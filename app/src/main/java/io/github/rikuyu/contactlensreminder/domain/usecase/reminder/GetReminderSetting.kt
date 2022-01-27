package io.github.rikuyu.contactlensreminder.domain.usecase.reminder

import io.github.rikuyu.contactlensreminder.domain.repository.ReminderRepository
import io.github.rikuyu.contactlensreminder.domain.ReminderValue

class GetReminderSetting(
    private val repository: ReminderRepository
) {
    operator fun invoke(): ReminderValue {
        return repository.getReminderSetting()
    }
}