package com.josealfonsomora.lastfmmusic.repository

import org.koin.dsl.module

fun provideRepositoryModule() = module {
    single { AlbumRepository(get()) }
}
