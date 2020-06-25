package com.josealfonsomora.lastfmmusic.repository

import com.josealfonsomora.lastfmmusic.businessentities.Album
import com.josealfonsomora.lastfmmusic.network.services.LastFmService
import io.reactivex.Single
import retrofit2.HttpException

class AlbumRepository(private val service: LastFmService) {

    fun getAlbums(): Single<List<Album>> =
        Single.fromCallable { service.getAlbums().execute() }
            .map { response ->
                if (!response.isSuccessful) throw HttpException(response)
                response.body()?.results?.albummatches?.albumList
            }

    fun getAlbumInfo(mbid: String): Single<Album> =
        Single.fromCallable { service.getAlbumInfo(mbid = mbid).execute() }
            .map { response ->
                if (!response.isSuccessful) throw HttpException(response)
                response.body()?.album
            }
}
