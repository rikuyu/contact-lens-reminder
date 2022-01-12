package com.example.contactlensreminder.domain.repository

import com.example.contactlensreminder.presentation.screens.top.ReminderValue

interface ReminderRepository {

    fun setNotificationData(reminderValue: ReminderValue)

    fun setReminder()

    fun getReminderSetting(): ReminderValue
}