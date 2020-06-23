package com.josealfonsomora.lastfmmusic.di

import com.josealfonsomora.lastfmmusic.LastFmMusicApplication
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Koin module for Core dependencies
 */
fun provideCoreModule() = module {
    single { androidApplication() as LastFmMusicApplication }
}
