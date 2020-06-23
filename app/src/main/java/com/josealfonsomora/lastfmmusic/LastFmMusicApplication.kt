package com.josealfonsomora.lastfmmusic

import android.app.Application
import com.josealfonsomora.lastfmmusic.di.setupKoin

class LastFmMusicApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }
}
