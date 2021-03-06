package io.github.rikuyu.contactlensreminder.data.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.rikuyu.contactlensreminder.data.local.LocalDataSource
import io.github.rikuyu.contactlensreminder.data.local.alarm.notification.NotificationAlarmManager
import io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown.TickDownAlarmManager
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.repository.AppSettingRepositoryImpl
import io.github.rikuyu.contactlensreminder.data.repository.LensSettingRepositoryImpl
import io.github.rikuyu.contactlensreminder.data.repository.ReminderRepositoryImpl
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEventService
import io.github.rikuyu.contactlensreminder.domain.local.DataSource
import io.github.rikuyu.contactlensreminder.domain.repository.AppSettingRepository
import io.github.rikuyu.contactlensreminder.domain.repository.LensSettingRepository
import io.github.rikuyu.contactlensreminder.domain.repository.ReminderRepository
import io.github.rikuyu.contactlensreminder.ui.util.AppReviewService
import io.github.rikuyu.contactlensreminder.ui.util.AppUpdateService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(localDataSource: LocalDataSource): DataSource

    @Binds
    @Singleton
    abstract fun bindSettingRepository(settingRepository: LensSettingRepositoryImpl): LensSettingRepository

    @Binds
    @Singleton
    abstract fun bindReminderRepository(reminderRepository: ReminderRepositoryImpl): ReminderRepository

    @Binds
    @Singleton
    abstract fun bindAppSettingRepository(appSettingRepository: AppSettingRepositoryImpl): AppSettingRepository

    @Module
    @InstallIn(SingletonComponent::class)
    internal object LocalUtilModule {

        @Provides
        @Singleton
        fun provideNotificationAlarmManager(
            @ApplicationContext context: Context,
            sharedPreferencesManager: SharedPreferencesManager,
        ): NotificationAlarmManager = NotificationAlarmManager(context, sharedPreferencesManager)

        @Provides
        @Singleton
        fun provideTickDownAlarmManager(
            @ApplicationContext context: Context
        ): TickDownAlarmManager = TickDownAlarmManager(context)

        @Provides
        @Singleton
        fun provideSharedPreferencesManager(
            @ApplicationContext context: Context,
        ): SharedPreferencesManager = SharedPreferencesManager(context)

        @Provides
        @Singleton
        fun provideFirebaseLogEvent(
            sharedPreferencesManager: SharedPreferencesManager,
        ): FirebaseLogEventService = FirebaseLogEventService(sharedPreferencesManager)

        @Provides
        @Singleton
        fun provideAppUpdateService(
            firebaseLogEventService: FirebaseLogEventService,
        ): AppUpdateService = AppUpdateService(firebaseLogEventService)

        @Provides
        @Singleton
        fun provideAppReviewService(
            firebaseLogEventService: FirebaseLogEventService,
        ): AppReviewService = AppReviewService(firebaseLogEventService)
    }
}