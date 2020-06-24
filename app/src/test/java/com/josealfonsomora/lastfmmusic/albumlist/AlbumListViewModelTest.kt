package com.josealfonsomora.lastfmmusic.albumlist

import com.josealfonsomora.lastfmmusic.businessentities.Album
import com.josealfonsomora.lastfmmusic.repository.AlbumRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.Test

class AlbumListViewModelTest {
    private val repository: AlbumRepository = mock()

    @Test
    fun loadsAlbumListFromRepository() {
        val list = listOf<Album>(mock())
        whenever(repository.getAlbums()).thenReturn(Single.just(list))

        val viewModel =
            AlbumListViewModel(repository, Schedulers.trampoline(), Schedulers.trampoline())

        assertEquals(list, viewModel.albumsList.get())

    }
}
