package com.example.contactlensreminder.domain.usecase.reminder

import com.example.contactlensreminder.domain.repository.ReminderRepository
import com.example.contactlensreminder.presentation.screens.top.ReminderValue

class SaveReminderSetting(
    private val repository: ReminderRepository
) {
    operator fun invoke(reminderValue: ReminderValue) {
        repository.saveReminderSetting(reminderValue)
    }
}