package com.example.contactlensreminder.data.repository

import com.example.contactlensreminder.data.local.LocalDataSource
import com.example.contactlensreminder.domain.ReminderValue
import com.example.contactlensreminder.domain.repository.ReminderRepository

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