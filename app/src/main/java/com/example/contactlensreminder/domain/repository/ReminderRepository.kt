package com.example.contactlensreminder.domain.repository

import com.example.contactlensreminder.presentation.screens.top.ReminderValue

interface ReminderRepository {

    fun saveReminderSetting(reminderValue: ReminderValue)

    fun startReminder()

    fun getReminderSetting(): ReminderValue

    fun cancelReminder()
}