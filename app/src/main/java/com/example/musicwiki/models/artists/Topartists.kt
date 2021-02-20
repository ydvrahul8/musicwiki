package com.example.musicwiki.models.artists

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Topartists(
    @SerializedName("@attr")
    @Expose
    val attr: Attr,
    val artist: List<Artist>
)