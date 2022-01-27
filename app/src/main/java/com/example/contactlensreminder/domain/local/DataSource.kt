package com.example.contactlensreminder.domain.local

import com.example.contactlensreminder.domain.ReminderValue
import com.example.contactlensreminder.domain.SettingValue

interface DataSource {
    fun saveReminderSetting(reminderValue: ReminderValue)

    fun startReminder(elapsedDays: Int)

    fun getReminderSetting(): ReminderValue

    fun cancelReminder()

    fun saveAllSetting(settingValue: SettingValue)

    fun getAllSetting(): SettingValue
}