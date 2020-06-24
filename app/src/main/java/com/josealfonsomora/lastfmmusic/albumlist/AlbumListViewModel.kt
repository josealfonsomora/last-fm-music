package com.josealfonsomora.lastfmmusic.albumlist

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.josealfonsomora.lastfmmusic.businessentities.Album
import com.josealfonsomora.lastfmmusic.core.extensions.disposeWith
import com.josealfonsomora.lastfmmusic.repository.AlbumRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class AlbumListViewModel(
    repository: AlbumRepository,
    ioScheduler: Scheduler = Schedulers.io(),
    uiScheduler: Scheduler = AndroidSchedulers.mainThread()
) : ViewModel() {
    private val TAG = "AlbumListViewModel"
    private val disposables = CompositeDisposable()

    val albumsList = ObservableField<List<Album>>()

    init {
        repository
            .getAlbums()
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
            .subscribe(
                {
                    if (it.isNotEmpty()) {
                        albumsList.set(it)
                    }
                },
                { exception ->
                    if (exception is HttpException) {
                        Log.e(TAG, exception.toString())
                    }
                }).disposeWith(disposables)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}
