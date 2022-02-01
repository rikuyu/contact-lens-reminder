package io.github.rikuyu.contactlensreminder.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.rikuyu.contactlensreminder.data.local.LocalDataSource
import io.github.rikuyu.contactlensreminder.data.local.alarm.notification.NotificationAlarmManager
import io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown.TickDownAlarmManager
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.repository.ReminderRepositoryImpl
import io.github.rikuyu.contactlensreminder.data.repository.SettingRepositoryImpl
import io.github.rikuyu.contactlensreminder.data.util.ChangeAppIconService
import io.github.rikuyu.contactlensreminder.domain.repository.ReminderRepository
import io.github.rikuyu.contactlensreminder.domain.repository.SettingRepository
import io.github.rikuyu.contactlensreminder.domain.usecase.reminder.*
import io.github.rikuyu.contactlensreminder.domain.usecase.setting.GetAllSetting
import io.github.rikuyu.contactlensreminder.domain.usecase.setting.LensSettingUseCase
import io.github.rikuyu.contactlensreminder.domain.usecase.setting.SaveAllSetting
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(
        sharedPreferencesManager: SharedPreferencesManager,
        tickDownAlarmManager: TickDownAlarmManager,
        notificationAlarmManager: NotificationAlarmManager,
        changeAppIconService: ChangeAppIconService
    ): LocalDataSource =
        LocalDataSource(
            tickDownAlarmManager = tickDownAlarmManager,
            sharedPreferencesManager = sharedPreferencesManager,
            notificationAlarmManager = notificationAlarmManager,
            changeAppIconService = changeAppIconService
        )

    @Provides
    @Singleton
    fun provideSettingRepository(
        localDataSource: LocalDataSource
    ): SettingRepository = SettingRepositoryImpl(localDataSource)

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
    fun provideNotificationAlarmManager(
        @ApplicationContext context: Context,
        sharedPreferencesManager: SharedPreferencesManager
    ): NotificationAlarmManager = NotificationAlarmManager(context, sharedPreferencesManager)

    @Provides
    @Singleton
    fun provideChangeAppIconService(
        @ApplicationContext context: Context
    ): ChangeAppIconService = ChangeAppIconService(context)

    @Provides
    @Singleton
    fun provideTickDownAlarmManager(
        @ApplicationContext context: Context
    ): TickDownAlarmManager = TickDownAlarmManager(context)

    @Provides
    @Singleton
    fun provideSharedPreferencesManager(
        @ApplicationContext context: Context
    ): SharedPreferencesManager =
        SharedPreferencesManager(context)

    @Provides
    @Singleton
    fun provideReminderRepository(
        localDataSource: LocalDataSource
    ): ReminderRepository = ReminderRepositoryImpl(localDataSource)

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