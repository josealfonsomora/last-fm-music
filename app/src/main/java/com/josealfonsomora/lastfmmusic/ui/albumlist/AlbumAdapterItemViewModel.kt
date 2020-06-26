package com.josealfonsomora.lastfmmusic.ui.albumlist

import com.josealfonsomora.lastfmmusic.businessentities.Album

class AlbumAdapterItemViewModel(
    val name: String,
    val artist: String,
    val url: String,
    val streamable: Boolean,
    val mbid: String,
    val images: List<Album.Image>
) {
    constructor(album: Album) : this(
        name = album.name,
        artist = album.artist,
        url = album.url,
        streamable = album.streamable,
        mbid = album.mbid,
        images = album.images
    )

    val imageToShow: String
        get() = images.first { it.size == "small" }.url
}
