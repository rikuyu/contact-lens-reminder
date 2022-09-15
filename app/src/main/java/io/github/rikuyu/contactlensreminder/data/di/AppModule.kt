package io.github.rikuyu.contactlensreminder.data.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.rikuyu.contactlensreminder.data.local.LocalDataSource
import io.github.rikuyu.contactlensreminder.data.local.alarm.notification.NotificationAlarmService
import io.github.rikuyu.contactlensreminder.data.local.alarm.notification.NotificationService
import io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown.TickDownAlarmService
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesService
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
            sharedPreferencesService: SharedPreferencesService,
        ): NotificationAlarmService = NotificationAlarmService(context, sharedPreferencesService)

        @Provides
        @Singleton
        fun provideTickDownAlarmManager(
            @ApplicationContext context: Context,
        ): TickDownAlarmService = TickDownAlarmService(context)

        @Provides
        @Singleton
        fun provideSharedPreferencesManager(
            @ApplicationContext context: Context,
        ): SharedPreferencesService = SharedPreferencesService(context)

        @Provides
        @Singleton
        fun provideFirebaseLogEvent(
            sharedPreferencesService: SharedPreferencesService,
        ): FirebaseLogEventService = FirebaseLogEventService(sharedPreferencesService)

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

        @Provides
        @Singleton
        fun provideNotificationService(
            @ApplicationContext context: Context,
        ): NotificationService = NotificationService(context)
    }
}