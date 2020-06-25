package com.josealfonsomora.lastfmmusic.albumlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.josealfonsomora.lastfmmusic.R
import com.josealfonsomora.lastfmmusic.albuminfo.AlbumInfoActivity
import com.josealfonsomora.lastfmmusic.databinding.ActivityAlbumListBinding
import org.koin.android.viewmodel.ext.android.viewModel

class AlbumListActivity : ComponentActivity() {
    private lateinit var binding: ActivityAlbumListBinding

    private val viewModel: AlbumListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_album_list)
        binding.lifecycleOwner = this

        binding.list.adapter =
            AlbumListAdapter { album -> AlbumInfoActivity.start(this, album.mbid) }

        binding.list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        binding.model = viewModel
    }
}
