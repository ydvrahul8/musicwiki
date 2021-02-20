package com.example.musicwiki.di

import androidx.lifecycle.ViewModelProvider
import com.example.musicwiki.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory):ViewModelProvider.Factory

}