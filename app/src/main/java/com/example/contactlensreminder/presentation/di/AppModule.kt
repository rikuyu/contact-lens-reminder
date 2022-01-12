package com.example.contactlensreminder.presentation.di

import android.content.Context
import com.example.contactlensreminder.data.repository.ReminderRepositoryImpl
import com.example.contactlensreminder.data.repository.SettingRepositoryImpl
import com.example.contactlensreminder.domain.repository.ReminderRepository
import com.example.contactlensreminder.domain.repository.SettingRepository
import com.example.contactlensreminder.domain.usecase.reminder.GetReminderSetting
import com.example.contactlensreminder.domain.usecase.reminder.ReminderUseCase
import com.example.contactlensreminder.domain.usecase.reminder.SetNotificationData
import com.example.contactlensreminder.domain.usecase.reminder.SetReminder
import com.example.contactlensreminder.domain.usecase.setting.GetAllSetting
import com.example.contactlensreminder.domain.usecase.setting.LensSettingUseCase
import com.example.contactlensreminder.domain.usecase.setting.SaveAllSetting
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
    fun provideSettingRepository(@ApplicationContext context: Context): SettingRepository =
        SettingRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideLensSettingUseCase(repository: SettingRepository): LensSettingUseCase {
        return LensSettingUseCase(
            saveAllSetting = SaveAllSetting(repository),
            getAllSetting = GetAllSetting(repository)
        )
    }

    @Provides
    @Singleton
    fun provideReminderRepository(@ApplicationContext context: Context): ReminderRepository =
        ReminderRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideReminderUseCase(repository: ReminderRepository): ReminderUseCase {
        return ReminderUseCase(
            setNotificationData = SetNotificationData(repository),
            setReminder = SetReminder(repository),
            getReminderSetting = GetReminderSetting(repository)
        )
    }
}