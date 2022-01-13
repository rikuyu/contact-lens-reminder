package com.example.contactlensreminder.domain.usecase.reminder

import com.example.contactlensreminder.domain.repository.ReminderRepository

class CancelReminder(
    private val repository: ReminderRepository
) {
    operator fun invoke() {
        repository.cancelReminder()
    }
}