package com.irfan.challenge7.ui.home

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.irfan.challenge7.App
import com.irfan.challenge7.R
import com.irfan.challenge7.databinding.FragmentHomeBinding
import com.irfan.challenge7.ui.ViewModelFactory
import com.irfan.challenge7.utils.EventObserver
import com.irfan.challenge7.utils.enableStatusBar
import com.irfan.challenge7.utils.initialLoadState
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()

    @Inject
    lateinit var factory: ViewModelFactory

    private val homeViewModel: HomeViewModel by viewModels {
        factory
    }

    private lateinit var movieAdapter: MovieAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getUserEmail().observe(viewLifecycleOwner) {
            val welcomeMessage = "Hello, $it"
            setupToolbar(welcomeMessage)
        }

        val window = (activity as AppCompatActivity).window
        enableStatusBar(true, view, window)

        setupAdapter()
        observeData()
    }

    private fun observeData() {
        homeViewModel.menuItemSelectedLiveData.observe(viewLifecycleOwner, EventObserver {
            onMenuItemSelected(it)
        })

        homeViewModel.popularMovies.observe(viewLifecycleOwner) {
            movieAdapter.submitData(lifecycle, it)
        }

        homeViewModel.navigateToDetail.observe(viewLifecycleOwner, EventObserver{
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(it)
            )
        })
    }

    private fun setupAdapter() {
        movieAdapter = MovieAdapter(homeViewModel::onMovieClicked)

        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = movieAdapter.withLoadStateFooter(
                    footer = LoadingStateAdapter {
                        movieAdapter.retry()
                    }
                )
            }
            movieAdapter.addLoadStateListener { combinedLoadStates ->
                layoutProgress.initialLoadState(combinedLoadStates.refresh) {
                    movieAdapter.retry()
                }
            }
        }
    }

    private fun setupToolbar(welcomeMessage: String) {
        binding.apply {
            toolbar.title = welcomeMessage
            appbarLayout.setOnApplyWindowInsetsListener { v, windowInsets ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    v.updatePadding(top = windowInsets.getInsets(WindowInsets.Type.systemBars()).top)
                } else {
                    v.updatePadding(top = windowInsets.systemWindowInsetTop)
                }
                windowInsets
            }
            toolbar.setOnMenuItemClickListener {
                homeViewModel.onMenuItemSelected(it.itemId)
                true
            }
        }

    }

    private fun onMenuItemSelected(actionId: Int) {
        when (actionId) {
            R.id.user -> {
                findNavController().navigate(R.id.action_homeFragment_to_updateProfileFragment)
            }
            R.id.favorite -> {
                findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
            }
        }
    }

//    private fun showSuccessState() {
//        binding.recyclerView.isVisible = true
//        binding.contentLoadingLayout.hide()
//    }
//
//    private fun showErrorState() {
//        binding.recyclerView.isVisible = true
//        binding.contentLoadingLayout.hide()
//    }
//
//    private fun showLoadingState() {
//        binding.recyclerView.isVisible = false
//        binding.contentLoadingLayout.show()
//    }

}