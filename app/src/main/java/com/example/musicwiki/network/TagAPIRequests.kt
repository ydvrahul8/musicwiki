package com.example.musicwiki.network

import com.example.musicwiki.models.tags.TagsModel
import retrofit2.http.GET

interface TagAPIRequests {

    @GET("/2.0/?method=tag.getTopTags")
    suspend fun getTopTags(): TagsModel

}