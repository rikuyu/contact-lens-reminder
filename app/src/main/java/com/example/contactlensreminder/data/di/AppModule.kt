package com.example.contactlensreminder.data.di

import android.content.Context
import com.example.contactlensreminder.data.repository.ReminderRepositoryImpl
import com.example.contactlensreminder.data.repository.SettingRepositoryImpl
import com.example.contactlensreminder.data.workmanager.NotificationWorkManagerService
import com.example.contactlensreminder.data.util.SharedPreferencesManager
import com.example.contactlensreminder.data.workmanager.TickDownWorkManagerService
import com.example.contactlensreminder.data.workmanager.WaitWorkManagerService
import com.example.contactlensreminder.domain.repository.ReminderRepository
import com.example.contactlensreminder.domain.repository.SettingRepository
import com.example.contactlensreminder.domain.usecase.reminder.*
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
    fun provideSettingRepository(
        sharedPreferencesManager: SharedPreferencesManager
    ): SettingRepository =
        SettingRepositoryImpl(sharedPreferencesManager)

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
    fun provideWaitWorkManagerService(
        @ApplicationContext context: Context
    ): WaitWorkManagerService =
        WaitWorkManagerService(context)

    @Provides
    @Singleton
    fun provideNotificationWorkManagerService(
        @ApplicationContext context: Context
    ): NotificationWorkManagerService =
        NotificationWorkManagerService(context)

    @Provides
    @Singleton
    fun provideTickDownWorkManagerService(
        @ApplicationContext context: Context
    ): TickDownWorkManagerService =
        TickDownWorkManagerService(context)

    @Provides
    @Singleton
    fun provideSharedPreferencesManager(
        @ApplicationContext context: Context
    ): SharedPreferencesManager =
        SharedPreferencesManager(context)

    @Provides
    @Singleton
    fun provideReminderRepository(
        waitWorkManagerService: WaitWorkManagerService,
        notificationWorkManagerService: NotificationWorkManagerService,
        sharedPreferencesManager: SharedPreferencesManager,
        tickDownWorkManagerService: TickDownWorkManagerService
    ): ReminderRepository = ReminderRepositoryImpl(
        waitWorkManagerService = waitWorkManagerService,
        notificationWorkManagerService = notificationWorkManagerService,
        tickDownWorkManagerService = tickDownWorkManagerService,
        sharedPreferencesManager = sharedPreferencesManager
    )

    @Provides
    @Singleton
    fun provideReminderUseCase(repository: ReminderRepository): ReminderUseCase {
        return ReminderUseCase(
            saveReminderSetting = SaveReminderSetting(repository),
            startReminder = StartReminder(repository),
            getReminderSetting = GetReminderSetting(repository),
            cancelReminder = CancelReminder(repository)
        )
    }
}