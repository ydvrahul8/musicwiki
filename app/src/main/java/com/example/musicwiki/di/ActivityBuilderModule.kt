package com.example.musicwiki.di

import com.example.musicwiki.di.genreDetail.GenreDetailFragmentBuilderModule
import com.example.musicwiki.di.genreDetail.GenreDetailModule
import com.example.musicwiki.di.genreDetail.GenreDetailScope
import com.example.musicwiki.di.genreDetail.GenreDetailViewModelModule
import com.example.musicwiki.di.tags.TagModule
import com.example.musicwiki.di.tags.TagScope
import com.example.musicwiki.di.tags.TagViewModelModule
import com.example.musicwiki.ui.genresDetail.GenreDetailActivity
import com.example.musicwiki.ui.tags.TagsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @GenreDetailScope
    @ContributesAndroidInjector(
        modules = [
            GenreDetailViewModelModule::class,
            GenreDetailModule::class,
            GenreDetailFragmentBuilderModule::class
        ]
    )
    abstract fun contributeGenreDetailActivity(): GenreDetailActivity

    @TagScope
    @ContributesAndroidInjector(
        modules = [
            TagViewModelModule::class,
            TagModule::class
        ]
    )
    abstract fun contributeTagActivity(): TagsActivity

}