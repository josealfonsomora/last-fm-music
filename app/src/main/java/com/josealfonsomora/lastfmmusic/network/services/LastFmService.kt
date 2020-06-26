package com.josealfonsomora.lastfmmusic.network.services

import com.google.gson.annotations.SerializedName
import com.josealfonsomora.lastfmmusic.BuildConfig
import com.josealfonsomora.lastfmmusic.businessentities.Album
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmService {

    @GET("/2.0/?method=album.search")
    fun getAlbums(
        @Query("api_key") key: String = BuildConfig.LASTFM_API_KEY,
        @Query("album") album: String = "",
        @Query("format") json: String = "json"
    ): Call<AlbumSearchResponse>

    @GET("/2.0/?method=album.getinfo")
    fun getAlbumInfo(
        @Query("api_key") key: String = BuildConfig.LASTFM_API_KEY,
        @Query("mbid") mbid: String? = null,
        @Query("album") name: String? = null,
        @Query("format") json: String = "json"
    ): Call<AlbumInfoResponse>
}

class AlbumSearchResponse(val results: Results)

class Results(
    @SerializedName("opensearch:totalResults")
    val totalResults: Long,
    @SerializedName("opensearch:startIndex")
    val startIndex: Long,
    @SerializedName("opensearch:itemsPerPage")
    val itemsPerPage: Long,
    val albummatches: Albummatches
)

class Albummatches(@SerializedName("album") val albumList: List<AlbumResponse>)

class AlbumInfoResponse(val album: AlbumResponse)

data class AlbumResponse(
    override val name: String,
    override val artist: String,
    override val url: String,
    override val streamable: Boolean,
    override val mbid: String,
    override val listeners: Long,
    override val playcount: Long,
    override val wiki: AlbumWiki,
    override val tracks: AlbumTracks,
    override val tags: AlbumTags,
    @SerializedName("image")
    override val images: List<Image>
) : Album

class Image(
    @SerializedName("#text") override val url: String,
    override val size: String
) : Album.Image

class AlbumTracks(
    override val track: List<AlbumTrack>
) : Album.Tracks

class AlbumTrack(
    override val name: String,
    override val url: String,
    override val duration: Long
) : Album.Tracks.Track

class AlbumTags(override val tag: List<AlbumTag>) : Album.Tags

class AlbumTag(override val name: String, override val url: String) : Album.Tags.Tag

class AlbumWiki(
    override val published: String,
    override val summary: String,
    override val content: String
) : Album.Wiki

