package com.example.contactlensreminder.domain.usecase.reminder

import com.example.contactlensreminder.domain.repository.ReminderRepository

class StartReminder(
    private val repository: ReminderRepository
) {
    operator fun invoke() {
        repository.startReminder()
    }
}