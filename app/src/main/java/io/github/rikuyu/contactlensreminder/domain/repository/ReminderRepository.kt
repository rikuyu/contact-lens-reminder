package io.github.rikuyu.contactlensreminder.domain.repository

import io.github.rikuyu.contactlensreminder.domain.ReminderValue

interface ReminderRepository {

    fun saveReminderSetting(reminderValue: ReminderValue)

    fun startReminder(elapsedDays: Int)

    fun getReminderSetting(): ReminderValue

    fun cancelReminder()
}