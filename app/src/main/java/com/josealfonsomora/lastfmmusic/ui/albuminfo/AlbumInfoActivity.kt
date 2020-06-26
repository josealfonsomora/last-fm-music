package com.josealfonsomora.lastfmmusic.ui.albuminfo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import com.josealfonsomora.lastfmmusic.R
import com.josealfonsomora.lastfmmusic.databinding.ActivityAlbumInfoBinding
import org.koin.android.viewmodel.ext.android.viewModel

class AlbumInfoActivity : ComponentActivity() {

    private val viewModel: AlbumInfoViewModel by viewModel()
    private lateinit var binding: ActivityAlbumInfoBinding

    private val mbid: String
        get() = intent.getStringExtra(EXTRA_MBID)!!

    private val name: String
        get() = intent.getStringExtra(EXTRA_NAME)!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_album_info)
        binding.lifecycleOwner = this

        binding.model = viewModel

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.showAlbumInfo(mbid, name)
    }

    companion object {
        private const val EXTRA_MBID = "EXTRA_MBID"
        private const val EXTRA_NAME = "EXTRA_NAME"

        fun start(activity: ComponentActivity, mbid: String, name: String) =
            activity.startActivity(Intent(activity, AlbumInfoActivity::class.java).apply {
                putExtra(EXTRA_MBID, mbid)
                putExtra(EXTRA_NAME, name)
            })
    }
}
