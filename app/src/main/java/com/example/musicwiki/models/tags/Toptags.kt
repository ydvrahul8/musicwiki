package com.example.musicwiki.models.tags

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Toptags(
    @SerializedName("@attr")
    @Expose
    val attr: Attr,
    val tag: List<Tag>
)