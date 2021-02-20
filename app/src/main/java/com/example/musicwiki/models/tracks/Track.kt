package com.example.musicwiki.models.tracks

data class Track(
    val artist: Artist?=null,
    val duration: String,
    val image: List<Image>?=null,
    val mbid: String,
    val name: String,
    val url: String
)