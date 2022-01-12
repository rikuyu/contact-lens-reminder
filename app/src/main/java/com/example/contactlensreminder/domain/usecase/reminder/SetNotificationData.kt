package com.example.contactlensreminder.domain.usecase.reminder

import com.example.contactlensreminder.domain.repository.ReminderRepository
import com.example.contactlensreminder.presentation.screens.top.ReminderValue

class SetNotificationData(
    private val repository: ReminderRepository
) {
    operator fun invoke(reminderValue: ReminderValue) {
        repository.setNotificationData(reminderValue)
    }
}