package io.github.rikuyu.contactlensreminder.data.repository

import io.github.rikuyu.contactlensreminder.data.local.LocalDataSource
import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue
import io.github.rikuyu.contactlensreminder.domain.repository.ReminderRepository

class ReminderRepositoryImpl(
    private val localDataSource: LocalDataSource
) : ReminderRepository {

    override fun saveReminderSetting(reminderValue: ReminderValue) {
        localDataSource.saveReminderSetting(reminderValue)
    }

    override fun startReminder(elapsedDays: Int) {
        localDataSource.startReminder(elapsedDays)
    }

    override fun getReminderSetting(): ReminderValue = localDataSource.getReminderSetting()

    override fun cancelReminder() {
        localDataSource.cancelReminder()
    }
}