package com.josealfonsomora.lastfmmusic.repository

import com.josealfonsomora.lastfmmusic.businessentities.Album
import com.josealfonsomora.lastfmmusic.network.services.LastFmService
import io.reactivex.Single
import retrofit2.HttpException

class AlbumRepository(private val service: LastFmService) {

    fun getAlbums(album: String): Single<List<Album>> =
        Single.fromCallable { service.getAlbums(album = album).execute() }
            .map { response ->
                if (!response.isSuccessful) throw HttpException(response)
                response.body()?.results?.albummatches?.albumList
            }

    fun getAlbumInfo(mbid: String = "", albumName: String = ""): Single<Album> =
        Single.fromCallable {
            if (mbid.isNotEmpty()) {
                service.getAlbumInfo(mbid = mbid)
            } else {
                service.getAlbumInfo(name = albumName)
            }.execute()
        }
            .map { response ->
                if (!response.isSuccessful) throw HttpException(response)
                response.body()?.album
            }
}
