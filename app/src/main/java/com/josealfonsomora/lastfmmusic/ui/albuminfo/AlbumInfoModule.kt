package com.josealfonsomora.lastfmmusic.ui.albuminfo

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun provideAlbumInfoModule() = module {
    viewModel { AlbumInfoViewModel(get()) }
}
