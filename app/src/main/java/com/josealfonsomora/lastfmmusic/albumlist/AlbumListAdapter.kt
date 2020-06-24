package com.josealfonsomora.lastfmmusic.albumlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.josealfonsomora.lastfmmusic.R
import com.josealfonsomora.lastfmmusic.BR
import com.josealfonsomora.lastfmmusic.businessentities.Album
import com.josealfonsomora.lastfmmusic.databinding.ItemAlbumBinding

class AlbumListAdapter : RecyclerView.Adapter<AlbumListAdapter.ViewHolder>() {
    private val items = mutableListOf<Album>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        val binding = DataBindingUtil.bind<ItemAlbumBinding>(rootView)!!
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val viewModel = AlbumAdapterItemViewModel(item)

        holder.viewModel = viewModel
        holder.binding.apply {
            setVariable(BR.model, viewModel)
            executePendingBindings()
        }
    }

    fun updateAlbuns(albums: List<Album>) {
        items.clear()
        items.addAll(albums)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var viewModel: AlbumAdapterItemViewModel
    }

}
