package com.josealfonsomora.lastfmmusic.core.bindingAdapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.josealfonsomora.lastfmmusic.albumlist.AlbumListAdapter
import com.josealfonsomora.lastfmmusic.businessentities.Album

@BindingAdapter("list")
fun bindingAdapterList(view: RecyclerView, list: List<Album>?) {
    list?.let {
        if(list.isNotEmpty()) {
            (view.adapter as AlbumListAdapter).updateAlbuns(it)
        }
    }
}