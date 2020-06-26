package com.josealfonsomora.lastfmmusic.ui.albumlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.josealfonsomora.lastfmmusic.BR
import com.josealfonsomora.lastfmmusic.R
import com.josealfonsomora.lastfmmusic.businessentities.Album
import com.josealfonsomora.lastfmmusic.databinding.ItemAlbumBinding

class AlbumListAdapter(private val onClickListener: (Album) -> Unit) :
    RecyclerView.Adapter<AlbumListAdapter.ViewHolder>() {
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
            holder.binding.root.setOnClickListener { onClickListener.invoke(item) }
            executePendingBindings()
        }

    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        Glide.with(holder.itemView.context).clear(holder.binding.image)
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
