package com.example.musicwiki.network

import com.example.musicwiki.models.album.TopAlbumModel
import com.example.musicwiki.models.artists.ArtistsModel
import com.example.musicwiki.models.artists.Topartists
import com.example.musicwiki.models.tagDetail.TagDetail
import com.example.musicwiki.models.tracks.TracksModel
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreDetailAPIRequest {

    @GET("/2.0/?method=tag.getinfo")
    suspend fun getTagDetails(@Query("tag") tag:String):TagDetail

    @GET("/2.0/?method=tag.gettopalbums")
    suspend fun getTopAlbumsList(@Query("tag") tag:String):TopAlbumModel

    @GET("/2.0/?method=tag.gettopartists")
    suspend fun getTopArtistsList(@Query("tag") tag:String):ArtistsModel

    @GET("/2.0/?method=tag.gettoptracks")
    suspend fun getTopTrackList(@Query("tag") tag:String):TracksModel

}