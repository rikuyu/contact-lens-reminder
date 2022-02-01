package io.github.rikuyu.contactlensreminder.domain.local

import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue
import io.github.rikuyu.contactlensreminder.domain.model.SettingValue

interface DataSource {
    fun saveReminderSetting(reminderValue: ReminderValue)

    fun startReminder(elapsedDays: Int)

    fun getReminderSetting(): ReminderValue

    fun cancelReminder()

    fun saveAllSetting(settingValue: SettingValue)

    fun getAllSetting(): SettingValue
}