package com.irfan.challenge7.ui.detail

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.irfan.challenge7.App
import com.irfan.challenge7.R
import com.irfan.challenge7.databinding.FragmentMovieDetailBinding
import com.irfan.challenge7.domain.model.Movie
import com.irfan.challenge7.ui.ViewModelFactory
import com.irfan.challenge7.ui.favorite.FavoriteViewModel
import com.irfan.challenge7.utils.DateFormatter
import com.irfan.challenge7.utils.enableStatusBar
import com.irfan.challenge7.utils.loadPhotoUrl
import javax.inject.Inject

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val binding: FragmentMovieDetailBinding by viewBinding()

    private val args by navArgs<MovieDetailFragmentArgs>()

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val window = (activity as AppCompatActivity).window

        var isToolbarShown = false

        if (!isToolbarShown) {
            enableStatusBar(false, view, window)
        }

        binding.apply {
            showDetails(args.movie)
            navigateUp.setOnClickListener {
                it.findNavController().popBackStack()
            }
            movieDetailScrollview.setOnScrollChangeListener(
                NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                    val shouldShowToolbar = scrollY > 60
                    if (isToolbarShown != shouldShowToolbar) {
                        isToolbarShown = shouldShowToolbar
                        binding.appbar.isActivated = shouldShowToolbar
                        binding.toolbarLayout.isTitleEnabled = shouldShowToolbar
                        binding.toolbarLayout.title = args.movie.originalTitle
                    }

                    if (scrollY > 60) {
                        enableStatusBar(true, view, window)
                        binding.navigateUp.setImageResource(R.drawable.ic_detail_back_on_surface)
                    } else if (oldScrollY > scrollY) {
                        enableStatusBar(false, view, window)
                        binding.navigateUp.setImageResource(R.drawable.ic_detail_back_surface)
                    }
                }
            )
        }
    }

    private fun showDetails(data: Movie) {
        binding.apply {
            detailImage.loadPhotoUrl(data.backdropPathUrl())
            titleTv.text = data.originalTitle
            voteTv.text = data.voteAverage
            releaseDateTv.text = DateFormatter.formatDate(data.releaseDate)
            overviewTv.text = data.overview
            posterImage.loadPhotoUrl(data.posterPathUrl())

            viewModel.isFavoriteMovie(data.id)

            viewModel.isFavoriteMovie.observe(viewLifecycleOwner) { isFavorite ->
                var statusFavorite = isFavorite
                setStatusFavorite(statusFavorite)
                fab.setOnClickListener {
                    statusFavorite = !statusFavorite
                    viewModel.setFavoriteTourism(data, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        Log.d("MovieDetailFragment", statusFavorite.toString())
        if (statusFavorite) {
            binding.fab.imageTintList = ColorStateList.valueOf(Color.rgb(255, 50, 50))
        } else {
            binding.fab.imageTintList = ColorStateList.valueOf(Color.rgb(255, 255, 255))
        }
    }
}