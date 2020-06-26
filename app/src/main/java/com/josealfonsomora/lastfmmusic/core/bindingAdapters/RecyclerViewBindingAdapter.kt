package com.josealfonsomora.lastfmmusic.core.bindingAdapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.josealfonsomora.lastfmmusic.ui.albumlist.AlbumListAdapter
import com.josealfonsomora.lastfmmusic.businessentities.Album

object RecyclerViewBindingAdapter {
    @JvmStatic
    @BindingAdapter("list")
    fun bindingAdapterList(view: RecyclerView, list: List<Album>?) {
        list?.let {
            if (list.isNotEmpty()) {
                (view.adapter as AlbumListAdapter).updateAlbuns(it)
            }
        }
    }
}
