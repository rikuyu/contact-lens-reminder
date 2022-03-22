package io.github.rikuyu.contactlensreminder.domain.local

import io.github.rikuyu.contactlensreminder.domain.model.LensSettingValue
import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue

interface DataSource {

    fun saveReminderSetting(reminderValue: ReminderValue)

    fun startReminder()

    fun getReminderSetting(): ReminderValue

    fun cancelReminder()

    fun saveAllLensSetting(lensSettingValue: LensSettingValue)

    fun getAllLensSetting(): LensSettingValue

    fun logEvent(label: String)

    fun getIsShowOnBoarding(): Boolean

    fun getIsDarkTheme(): Boolean

    fun saveIsDarkTheme()

    fun getThemeColor(): String

    fun saveThemeColor(color: String)
}