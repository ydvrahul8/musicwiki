package com.example.musicwiki.di.tags

import com.example.musicwiki.network.TagAPIRequests
import com.example.musicwiki.ui.adapter.TagsAdapter
import com.example.musicwiki.ui.adapter.TrackAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object TagModule {

    @TagScope
    @Provides
    @JvmStatic
    fun provideTagAdapter(): TagsAdapter {
        return TagsAdapter()
    }

    @TagScope
    @Provides
    @JvmStatic
    fun provideTrackAdapter(): TrackAdapter {
        return TrackAdapter()
    }

    @TagScope
    @Provides
    @JvmStatic
    fun provideApiRequests(retrofit: Retrofit): TagAPIRequests {
        return retrofit.create(TagAPIRequests::class.java)
    }
}