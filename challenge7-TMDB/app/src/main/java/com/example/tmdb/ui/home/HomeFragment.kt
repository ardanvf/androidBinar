package com.example.tmdb.ui.home

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.BuildConfig
import com.facebook.shimmer.ShimmerFrameLayout
import com.example.tmdb.R
import com.example.tmdb.data.dao.UserViewModel
import com.example.tmdb.data.preferences.Constant
import com.example.tmdb.data.preferences.Helper
import com.example.tmdb.databinding.FragmentHomeBinding
import com.example.tmdb.ui.favorite.MovieVerticalAdapter
import com.example.tmdb.ui.shared.model.MovieItemModel
import com.example.tmdb.ui.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home), Toolbar.OnMenuItemClickListener {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val navController by lazy { findNavController() }
    private val viewModel: HomeViewModel by viewModel()
    private val viewModel2: UserViewModel by viewModel()
    private lateinit var sharedPref: Helper



    private val popularAdapter = MovieVerticalAdapter(::onMovieItemClicked)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = Helper(requireContext())
        doubleBackToExit()
        setupRecyclerView()
        setupObserver()
        setupListener()
        initData()
        getName()
        binding.profilers.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
        binding.fav.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoritesFragment)

        }
    }
    private fun getName() {
        lifecycleScope.launch(Dispatchers.IO) {
            val name = viewModel2.getUserName(sharedPref.getEmail(Constant.EMAIL_USER)!!)
            activity?.runOnUiThread {
                binding.username.text = "Welcome,$name"

            }
        }
    }

    private fun setupRecyclerView() {
        with(binding) {
            rvPopular.adapter = popularAdapter
                  }
    }
    private fun doubleBackToExit() {
        var doubleBackPressed: Long = 0
        val toast = Toast.makeText(requireContext(), "${BuildConfig.APPLICATION_ID}", Toast.LENGTH_SHORT)
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity()) {
            if (doubleBackPressed + 2000 > System.currentTimeMillis()) {
                activity?.finish()
                toast.cancel()
            } else {
                toast.show()
            }
            doubleBackPressed = System.currentTimeMillis()
        }
    }


    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.fav -> {
                navController.navigate(
                    HomeFragmentDirections.actionHomeFragmentToFavoritesFragment()
                )
                true
            }
            R.id.profilers ->{
                toprofife()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupObserver() {
        observeStateOf(viewModel.popularMovies, ::handlePopularState)
    }
    private fun toprofife(){
        lifecycleScope.launch(Dispatchers.IO) {
            val data3 = viewModel2.getUserProfile(sharedPref.getEmail(Constant.EMAIL_USER))
            activity?.runOnUiThread {
                val actionToProfile = HomeFragmentDirections.actionHomeFragmentToProfileFragment(data3)
                findNavController().navigate(actionToProfile)
            }
        }
    }

    private fun handlePopularState(state: UIState<List<MovieItemModel>?>) {
        when (state) {
            UIState.Loading -> {
                with(binding) {
                    showLoading(shimmerPopular, rvPopular, layoutPopularError.root)
                }
            }
            is UIState.Success -> {
                with(binding) {
                    hideLoading(shimmerPopular, rvPopular)
                    state.data?.let { popularAdapter.submitList(it) }
                }
            }
            is UIState.Failure -> {
                with(binding) {
                    hideLoading(shimmerPopular, rvPopular, false)
                    layoutPopularError.root.setVisible()
                }
            }
        }
    }

    private fun setupListener() {
        with(binding) {
            layoutPopularError.btnReload.setOnClickListener {
                viewModel.popularMovies.load()
            }


        }
    }

    private fun initData() {
        viewModel.popularMovies.load()
    }

    private fun onMovieItemClicked(movie: MovieItemModel) {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie))
    }

    private fun showLoading(
        shimmerView: ShimmerFrameLayout,
        recyclerView: RecyclerView,
        errorView: View
    ) {
        shimmerView.setVisible()
        shimmerView.startShimmer()
        recyclerView.setGone()
        errorView.setGone()
    }

    private fun hideLoading(
        shimmerView: ShimmerFrameLayout,
        recyclerView: RecyclerView,
        showRv: Boolean = true
    ) {
        shimmerView.setGone()
        shimmerView.stopShimmer()
        if (showRv) recyclerView.setVisible()
    }

}