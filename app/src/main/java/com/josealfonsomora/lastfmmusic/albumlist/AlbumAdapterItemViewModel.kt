package com.josealfonsomora.lastfmmusic.albumlist

import com.josealfonsomora.lastfmmusic.businessentities.Album

class AlbumAdapterItemViewModel(
    override val name: String,
    override val artist: String,
    override val url: String,
    override val streamable: Boolean,
    override val mbid: String,
    override val image: List<Album.Image>
) : Album {
    constructor(album: Album) : this(
        name = album.name,
        artist = album.artist,
        url = album.url,
        streamable = album.streamable,
        mbid = album.mbid,
        image = album.image
    )

    val imageToShow: String
        get() = image.first { it.size == "small" }.url
}
