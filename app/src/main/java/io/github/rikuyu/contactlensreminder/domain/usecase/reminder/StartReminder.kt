package io.github.rikuyu.contactlensreminder.domain.usecase.reminder

import io.github.rikuyu.contactlensreminder.domain.repository.ReminderRepository
import javax.inject.Inject

class StartReminder @Inject constructor(
    private val repository: ReminderRepository
) {
    operator fun invoke() {
        repository.startReminder()
    }
}