package com.josealfonsomora.lastfmmusic.albuminfo

import com.josealfonsomora.lastfmmusic.businessentities.Album
import com.josealfonsomora.lastfmmusic.network.services.Image
import com.josealfonsomora.lastfmmusic.repository.AlbumRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Test

class AlbumInfoViewModelTest {

    private val repository: AlbumRepository = mock()

    private val underTest = AlbumInfoViewModel(
        repository = repository,
        ioScheduler = Schedulers.trampoline(),
        uiScheduler = Schedulers.trampoline()
    )

    @Test
    fun loadsInformationOfAlbun() {
        val album: Album = mock {
            on { name }.thenReturn("name")
            on { images }.thenReturn(
                listOf(
                    Image("image-small", "small"),
                    Image("image-medium", "medium"),
                    Image("image-large", "large"),
                    Image("image-extralarge", "extralarge")
                )
            )
        }
        whenever(repository.getAlbumInfo(any(), any())).thenReturn(Single.just(album))

        underTest.showAlbumInfo("mdib", "album")

        assertEquals("name", underTest.name.get())
        assertEquals("image-large", underTest.image.get())
    }
}
