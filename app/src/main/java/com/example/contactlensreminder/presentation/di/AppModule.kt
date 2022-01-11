package com.example.contactlensreminder.presentation.di

import android.content.Context
import com.example.contactlensreminder.data.repository.MainRepositoryImpl
import com.example.contactlensreminder.domain.repository.MainRepository
import com.example.contactlensreminder.domain.usecase.setting.GetAllSetting
import com.example.contactlensreminder.domain.usecase.setting.LensSettingUseCase
import com.example.contactlensreminder.domain.usecase.setting.SaveLensPower
import com.example.contactlensreminder.domain.usecase.setting.SetAllSetting
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(@ApplicationContext context: Context): MainRepository =
        MainRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideLensSettingUseCase(repository: MainRepository): LensSettingUseCase {
        return LensSettingUseCase(
            setAllSetting = SetAllSetting(repository),
            getAllSetting = GetAllSetting(repository),
            saveLensPower = SaveLensPower(repository)
        )
    }
}