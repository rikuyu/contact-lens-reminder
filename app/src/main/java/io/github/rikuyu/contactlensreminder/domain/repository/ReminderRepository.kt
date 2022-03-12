package io.github.rikuyu.contactlensreminder.domain.repository

import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue

interface ReminderRepository {

    fun saveReminderSetting(reminderValue: ReminderValue)

    fun startReminder()

    fun getReminderSetting(): ReminderValue

    fun cancelReminder()

    fun getIsShowOnBoarding(): Boolean

    fun getIsDarkTheme(): Boolean

    fun switchIsDarkTheme()
}