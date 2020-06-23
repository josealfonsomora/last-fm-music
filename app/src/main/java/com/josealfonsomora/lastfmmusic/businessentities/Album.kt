package com.josealfonsomora.lastfmmusic.businessentities

interface Album {
    val name: String
    val artist: String
    val url: String
    val streamable: Boolean
    val mbid: String
    val image: List<Image>

    interface Image {
        val url: String
        val size: String
    }
}
