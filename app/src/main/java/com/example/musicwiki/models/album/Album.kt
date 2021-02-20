package com.example.musicwiki.models.album

data class Album(
    val artist: Artist?=null,
    val image: List<Image>?=null,
    val mbid: String,
    val name: String,
    val url: String
)