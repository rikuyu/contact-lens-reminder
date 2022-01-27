package io.github.rikuyu.contactlensreminder.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.rikuyu.contactlensreminder.data.local.LocalDataSource
import io.github.rikuyu.contactlensreminder.data.local.alarm.AlarmManagerService
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.local.workmanager.TickDownWorkManagerService
import io.github.rikuyu.contactlensreminder.data.repository.ReminderRepositoryImpl
import io.github.rikuyu.contactlensreminder.data.repository.SettingRepositoryImpl
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
        tickDownWorkManagerService: TickDownWorkManagerService,
        alarmManagerService: AlarmManagerService
    ): LocalDataSource =
        LocalDataSource(
            tickDownWorkManagerService = tickDownWorkManagerService,
            sharedPreferencesManager = sharedPreferencesManager,
            alarmManagerService = alarmManagerService
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
    fun provideTickDownWorkManagerService(
        @ApplicationContext context: Context
    ): TickDownWorkManagerService =
        TickDownWorkManagerService(context)

    @Provides
    @Singleton
    fun provideAlarmManagerService(
        @ApplicationContext context: Context,
        sharedPreferencesManager: SharedPreferencesManager
    ): AlarmManagerService = AlarmManagerService(context, sharedPreferencesManager)

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