package com.example.tmdb.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import coil.api.load
import com.google.android.material.snackbar.Snackbar
import com.example.tmdb.R
import com.example.tmdb.databinding.FragmentDetailBinding
import com.example.tmdb.ui.shared.model.MovieItemModel
import com.example.tmdb.ui.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val navController by lazy { findNavController() }
    private val detailArgs by navArgs<DetailFragmentArgs>()
    private val viewModel: DetailViewModel by viewModel()
    private val adapter = ReviewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(detailArgs.movieItem)
        setupRecyclerView()
        setupObserver()
        setupListener()
        doubleBackToExit()
        viewModel.init(detailArgs.movieItem)
        viewModel.reviews.load()
        viewModel.checkIsFavorite()
    }

    private fun setupUI(movieItem: MovieItemModel) {
        with(binding) {
            val appBarConfiguration = AppBarConfiguration(navController.graph)
            toolbar.setupWithNavController(navController, appBarConfiguration)
            toolbar.title = movieItem.title
            ivBackdrop.load(movieItem.backdropPath) {
                crossfade(true)
                placeholder(R.drawable.placeholder_poster_landscape)
            }
            layoutImagePoster.ivDetailPoster.load(movieItem.posterPath) {
                crossfade(true)
                placeholder(R.drawable.placeholder_poster_portrait)
            }
            tvDetailRating.text = movieItem.voteAverage.toString()
            tvDetailReleaseDate.text = movieItem.releaseDate
            tvDetailVoteCount.text = "${movieItem.voteCount} votes"
            tvDetailDesc.apply {
                addShowMoreText(getString(R.string.label_text_expand))
                addShowLessText(getString(R.string.label_text_collapse))
                setShowMoreColor(ContextCompat.getColor(root.context, R.color.colorAccentDark))
                setShowLessTextColor(ContextCompat.getColor(root.context, R.color.colorAccentDark))
                setShowingLine(9)
            }
            tvDetailDesc.text = movieItem.overview
        }
    }

    private fun setupRecyclerView() {
        binding.rvReviews.apply {
            addItemDecoration(
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            )
            adapter = this@DetailFragment.adapter

        }
    }

    private fun setupObserver() {
        viewModel.reviews.state.observe(viewLifecycleOwner) {
            when (it) {
                UIState.Loading -> {
                    showLoading()
                }
                is UIState.Success -> {
                    with(binding) {
                        hideLoading()
                        rvReviews.setVisible()
                        it.data?.let {
                            tvReviewTitle.text = "Review (${it.size})"
                            adapter.submitList(it)
                            if (it.isEmpty()) viewEmptyReview.setVisible()
                        }
                    }
                }
                is UIState.Failure -> {
                    hideLoading()
                    binding.layoutReviewError.root.setVisible()
                }
            }
        }
        viewModel.isFavorite.observe(viewLifecycleOwner) {
            binding.bottomBar.menu
                .findItem(R.id.action_detail_favorite)
                .setIcon(
                    if (it) {
                        R.drawable.ic_favorite
                    } else {
                        R.drawable.ic_favorite_border
                    }
                )
        }
        viewModel.snackbar.observeEvent(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).apply {
                setAnchorView(R.id.bottom_bar)
                show()
            }
        }
    }
    private fun doubleBackToExit() {
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity()) {
            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }
    }
    private fun setupListener() {
        binding.layoutReviewError.btnReload.setOnClickListener {
            viewModel.reviews.load()
        }
        binding.bottomBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_detail_favorite -> {
                    viewModel.onFavoriteClick()
                    true
                }
                R.id.action_detail_share -> {
                    shareMovie()
                    true
                }
                else -> false
            }
        }
    }

    private fun shareMovie() {
        val message = with(detailArgs.movieItem) {
            String.format(getString(R.string.share_message), title, voteAverage, releaseDate)
        }
        ShareCompat.IntentBuilder.from(requireActivity())
            .setType("text/plain")
            .setChooserTitle(getString(R.string.share_title))
            .setText(message)
            .startChooser()
    }

    private fun showLoading() {
        binding.shimmerReview.setVisible()
        binding.shimmerReview.startShimmer()
        binding.rvReviews.setGone()
        binding.viewEmptyReview.setGone()
        binding.layoutReviewError.root.setGone()
    }

    private fun hideLoading() {
        binding.shimmerReview.setGone()
        binding.shimmerReview.stopShimmer()
    }

}