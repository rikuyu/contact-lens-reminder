package io.github.rikuyu.contactlensreminder.domain.local

import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue
import io.github.rikuyu.contactlensreminder.domain.model.LensSettingValue

interface DataSource {

    fun saveReminderSetting(reminderValue: ReminderValue)

    fun startReminder()

    fun getReminderSetting(): ReminderValue

    fun cancelReminder()

    fun saveAllLensSetting(lensSettingValue: LensSettingValue)

    fun getAllLensSetting(): LensSettingValue
}