package com.example.musicwiki.models.album

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Albums(
    @SerializedName("@attr")
    @Expose
    val attr: Attr,
    val album: List<Album>
)