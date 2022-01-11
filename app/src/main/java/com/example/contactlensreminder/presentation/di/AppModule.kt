package com.example.contactlensreminder.presentation.di

import com.example.contactlensreminder.data.repository.MainRepositoryImpl
import com.example.contactlensreminder.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(): MainRepository = MainRepositoryImpl()
}