package com.example.musicwiki.di.genreDetail

import com.example.musicwiki.di.tags.TagScope
import com.example.musicwiki.network.GenreDetailAPIRequest
import com.example.musicwiki.network.TagAPIRequests
import com.example.musicwiki.ui.adapter.AlbumAdapter
import com.example.musicwiki.ui.adapter.ArtistAdapter
import com.example.musicwiki.ui.adapter.TagsAdapter
import com.example.musicwiki.ui.adapter.TrackAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object GenreDetailModule {
    @GenreDetailScope
    @Provides
    @JvmStatic
    fun provideGenreDetailApiRequests(retrofit: Retrofit): GenreDetailAPIRequest {
        return retrofit.create(GenreDetailAPIRequest::class.java)
    }

    @GenreDetailScope
    @Provides
    @JvmStatic
    fun provideAlbumAdapter(): AlbumAdapter {
        return AlbumAdapter()
    }

    @GenreDetailScope
    @Provides
    @JvmStatic
    fun provideArtistAdapter():ArtistAdapter {
        return ArtistAdapter()
    }

    @GenreDetailScope
    @Provides
    @JvmStatic
    fun provideTrackAdapter():TrackAdapter {
        return TrackAdapter()
    }
}