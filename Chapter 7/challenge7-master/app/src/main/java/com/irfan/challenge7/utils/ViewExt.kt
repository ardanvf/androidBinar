package com.irfan.challenge7.utils

import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.google.android.material.snackbar.Snackbar
import com.irfan.challenge7.databinding.ItemLoadingBinding

fun View.showSnackBar(
    message: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    anchor: View? = null
) {
    Snackbar.make(this, message, duration)
        .setAnchorView(anchor)
        .show()
}

fun ItemLoadingBinding.initialLoadState(state: LoadState, retry: () -> Unit) = run {
    if (state is LoadState.Loading) progressBar.show() else progressBar.hide()
    retryButton.isVisible = state is LoadState.Error
    errorMsg.isVisible = state is LoadState.Error

    if (state is LoadState.Error) {
        val msg = state.error.localizedMessage
        errorMsg.text = msg
    }

    retryButton.setOnClickListener {
        retry.invoke()
    }
}