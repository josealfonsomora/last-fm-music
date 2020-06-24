package com.josealfonsomora.lastfmmusic.albumlist

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun provideAlbumListModule() = module {
    viewModel { AlbumListViewModel(get()) }
}
