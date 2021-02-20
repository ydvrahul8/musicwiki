package com.example.musicwiki.di.genreDetail

import com.example.musicwiki.ui.genresDetail.fragments.AlbumFragment
import com.example.musicwiki.ui.genresDetail.fragments.ArtistFragment
import com.example.musicwiki.ui.genresDetail.fragments.TrackFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GenreDetailFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeAlbumFragment(): AlbumFragment

    @ContributesAndroidInjector
    abstract fun contributeArtistFragment(): ArtistFragment

    @ContributesAndroidInjector
    abstract fun contributeTrackFragment(): TrackFragment

}