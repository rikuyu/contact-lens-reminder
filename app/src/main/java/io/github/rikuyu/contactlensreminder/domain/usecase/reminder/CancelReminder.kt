package io.github.rikuyu.contactlensreminder.domain.usecase.reminder

import io.github.rikuyu.contactlensreminder.domain.repository.ReminderRepository

class CancelReminder(
    private val repository: ReminderRepository
) {
    operator fun invoke() {
        repository.cancelReminder()
    }
}