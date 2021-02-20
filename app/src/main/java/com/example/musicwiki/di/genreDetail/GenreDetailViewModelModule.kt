package com.example.musicwiki.di.genreDetail

import androidx.lifecycle.ViewModel
import com.example.musicwiki.di.ViewModelKey
import com.example.musicwiki.ui.genresDetail.GenreDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GenreDetailViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(GenreDetailViewModel::class)
    abstract fun bindGenreDetailViewModel(viewModel: GenreDetailViewModel):ViewModel
}