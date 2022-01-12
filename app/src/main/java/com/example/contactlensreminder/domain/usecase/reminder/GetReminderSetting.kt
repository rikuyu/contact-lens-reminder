package com.example.contactlensreminder.domain.usecase.reminder

import com.example.contactlensreminder.domain.repository.ReminderRepository
import com.example.contactlensreminder.presentation.screens.top.ReminderValue

class GetReminderSetting(
    private val repository: ReminderRepository
) {
    operator fun invoke(): ReminderValue {
        return repository.getReminderSetting()
    }
}