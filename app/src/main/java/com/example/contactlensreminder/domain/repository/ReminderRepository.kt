package com.example.contactlensreminder.domain.repository

import com.example.contactlensreminder.domain.ReminderValue

interface ReminderRepository {

    fun saveReminderSetting(reminderValue: ReminderValue)

    fun startReminder(lensPeriod: Int)

    fun getReminderSetting(): ReminderValue

    fun cancelReminder()
}