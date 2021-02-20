package com.example.musicwiki.models.artists

data class Artist(
    val image: List<Image>?=null,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
)