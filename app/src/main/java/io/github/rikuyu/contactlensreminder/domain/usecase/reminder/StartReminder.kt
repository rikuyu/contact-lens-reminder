package io.github.rikuyu.contactlensreminder.domain.usecase.reminder

import io.github.rikuyu.contactlensreminder.domain.repository.ReminderRepository

class StartReminder(
    private val repository: ReminderRepository
) {
    operator fun invoke(elapsedDays: Int) {
        repository.startReminder(elapsedDays)
    }
}