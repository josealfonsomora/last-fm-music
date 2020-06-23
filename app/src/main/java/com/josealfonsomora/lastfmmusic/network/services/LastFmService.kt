package com.josealfonsomora.lastfmmusic.network.services

import com.google.gson.annotations.SerializedName
import com.josealfonsomora.lastfmmusic.BuildConfig
import com.josealfonsomora.lastfmmusic.businessentities.Album
import retrofit2.Call
import retrofit2.http.GET

interface LastFmService {

    @GET("/2.0/?method=album.search&album=believe&api_key=${BuildConfig.LASTFM_API_KEY}&format=json")
    fun getAlbums(): Call<AlbumSearchResponse>
}

class AlbumSearchResponse(val results: AlbumSearchResponse.Results) {
    inner class Results(
        @SerializedName("opensearch:totalResults")
        val totalResults: Long,
        @SerializedName("opensearch:startIndex")
        val startIndex: Long,
        @SerializedName("opensearch:itemsPerPage")
        val itemsPerPage: Long,
        val albummatches: AlbumSearchResponse.Results.Albummatches
    ) {
        inner class Albummatches(@SerializedName("album") val albumList: List<AlbumResponse>) {
            inner class AlbumResponse(
                override val name: String,
                override val artist: String,
                override val url: String,
                override val streamable: Boolean,
                override val mbid: String,
                override val image: List<AlbumResponse.Image>
            ) : Album {
                inner class Image(
                    @SerializedName("#text") override val url: String,
                    override val size: String
                ) : Album.Image
            }
        }
    }
}

