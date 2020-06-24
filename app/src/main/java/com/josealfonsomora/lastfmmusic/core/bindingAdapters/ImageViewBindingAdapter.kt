package com.josealfonsomora.lastfmmusic.core.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.josealfonsomora.lastfmmusic.R

object ImageViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl", "imageTransition", requireAll = false)
    fun loadImage(view: ImageView, imageUrl: String?, transitionName: String?) {
        imageUrl?.let {
            view.transitionName = transitionName ?: ""
            Glide.with(view.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        }
    }
}
