package com.josealfonsomora.lastfmmusic.ui.albumlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.josealfonsomora.lastfmmusic.R
import com.josealfonsomora.lastfmmusic.core.extensions.disposeWith
import com.josealfonsomora.lastfmmusic.databinding.ActivityAlbumListBinding
import com.josealfonsomora.lastfmmusic.ui.albuminfo.AlbumInfoActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

const val VIEW_CLICK_THROTTLE_DELAY_MS = 300L

class AlbumListActivity : ComponentActivity() {
    private lateinit var binding: ActivityAlbumListBinding
    private val disposables = CompositeDisposable()

    private val viewModel: AlbumListViewModel by viewModel()
    private val emitter = PublishSubject.create<CharSequence>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_album_list)
        binding.lifecycleOwner = this

        binding.list.adapter =
            AlbumListAdapter { album -> AlbumInfoActivity.start(this, album.mbid, album.name) }

        binding.list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        binding.model = viewModel

        binding.toolbar.title = getString(R.string.app_name)

        binding.searchBar.doOnTextChanged { text, _, _, _ ->
            text?.let { emitter.onNext(text) }
        }

        emitter
            .throttleFirst(VIEW_CLICK_THROTTLE_DELAY_MS, TimeUnit.MILLISECONDS, Schedulers.io())
            .subscribe {
                viewModel.searchAlbum(it)
            }.disposeWith(disposables)

    }
}
