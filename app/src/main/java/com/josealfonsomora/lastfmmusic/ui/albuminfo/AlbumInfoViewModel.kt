package com.josealfonsomora.lastfmmusic.ui.albuminfo

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.josealfonsomora.lastfmmusic.core.extensions.disposeWith
import com.josealfonsomora.lastfmmusic.repository.AlbumRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AlbumInfoViewModel(
    private val repository: AlbumRepository,
    private val ioScheduler: Scheduler = Schedulers.io(),
    private val uiScheduler: Scheduler = AndroidSchedulers.mainThread()
) : ViewModel() {

    private val disposables = CompositeDisposable()

    val name = ObservableField<String>()
    val image = ObservableField<String>()
    val imageTransitionName = ObservableField<String>()
    val content = ObservableField<String>()

    fun showAlbumInfo(mbid: String, albumName:String, transitionName: String = "") {
        repository
            .getAlbumInfo(mbid, albumName)
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe({ album ->
                name.set(album.name)
                image.set(album.images.first { it.size == "large" }.url)
                imageTransitionName.set(transitionName)
                content.set(album.wiki?.content?:"")
            }, {

            })
            .disposeWith(disposables)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}
