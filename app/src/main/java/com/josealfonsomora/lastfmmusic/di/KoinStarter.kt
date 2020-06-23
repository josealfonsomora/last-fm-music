package com.josealfonsomora.lastfmmusic.di

import com.josealfonsomora.lastfmmusic.LastFmMusicApplication
import com.josealfonsomora.lastfmmusic.network.provideNetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


/**
 * Extension function to start koin dependency injector
 * This function
 */
fun LastFmMusicApplication.setupKoin() {
    startKoin {
        androidLogger()
        androidContext(this@setupKoin)
        modules(
            listOf(
                provideCoreModule(),
                provideNetworkModule()
            )
        )
    }
}