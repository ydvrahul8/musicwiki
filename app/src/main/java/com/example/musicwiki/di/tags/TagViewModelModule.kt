package com.example.musicwiki.di.tags

import androidx.lifecycle.ViewModel
import com.example.musicwiki.di.ViewModelKey
import com.example.musicwiki.ui.tags.TagsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TagViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TagsViewModel::class)
    abstract fun bindTagViewModel(viewModel: TagsViewModel): ViewModel
}