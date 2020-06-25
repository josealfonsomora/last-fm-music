package com.josealfonsomora.lastfmmusic.businessentities

interface Album {
    val name: String
    val artist: String
    val url: String
    val streamable: Boolean
    val mbid: String
    val images: List<Image>
    val listeners: Long
    val playcount: Long
    val tracks: Tracks
    val tags: Tags
    val wiki: Wiki

    interface Wiki {
        val published: String
        val summary: String
        val content: String
    }

    interface Image {
        val url: String
        val size: String
    }

    interface Tracks {
        val track: List<Track>

        interface Track {
            val name: String
            val url: String
            val duration: Long
        }
    }

    interface Tags {
        val tag: List<Tag>

        interface Tag {
            val name: String
            val url: String
        }
    }
}
