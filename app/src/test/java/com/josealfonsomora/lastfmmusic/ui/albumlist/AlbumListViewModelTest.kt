package com.josealfonsomora.lastfmmusic.ui.albumlist

import com.josealfonsomora.lastfmmusic.businessentities.Album
import com.josealfonsomora.lastfmmusic.repository.AlbumRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Test

class AlbumListViewModelTest {
    private val repository: AlbumRepository = mock()

    @Test
    fun loadsAlbumListFromRepository() {
        val list = listOf<Album>(mock())
        whenever(repository.getAlbums("album")).thenReturn(Single.just(list))

        val viewModel =
            AlbumListViewModel(repository, Schedulers.trampoline(), Schedulers.trampoline())

        viewModel.searchAlbum("album")
        assertEquals(list, viewModel.albumsList.get())
    }
}
