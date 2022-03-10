package io.github.rikuyu.contactlensreminder.data.repository

import io.github.rikuyu.contactlensreminder.domain.local.DataSource
import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue
import io.github.rikuyu.contactlensreminder.domain.repository.ReminderRepository
import javax.inject.Inject

class ReminderRepositoryImpl @Inject constructor(
    private val localDataSource: DataSource
) : ReminderRepository {

    override fun saveReminderSetting(reminderValue: ReminderValue) {
        localDataSource.saveReminderSetting(reminderValue)
    }

    override fun startReminder() {
        localDataSource.startReminder()
    }

    override fun getReminderSetting(): ReminderValue = localDataSource.getReminderSetting()

    override fun cancelReminder() {
        localDataSource.cancelReminder()
    }

    override fun getIsShowOnBoarding() = localDataSource.getIsShowOnBoarding()
}