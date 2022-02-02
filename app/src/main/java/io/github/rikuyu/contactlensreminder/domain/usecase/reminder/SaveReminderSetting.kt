package io.github.rikuyu.contactlensreminder.domain.usecase.reminder

import io.github.rikuyu.contactlensreminder.domain.repository.ReminderRepository
import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue
import javax.inject.Inject

class SaveReminderSetting @Inject constructor(
    private val repository: ReminderRepository
) {
    operator fun invoke(reminderValue: ReminderValue) {
        repository.saveReminderSetting(reminderValue)
    }
}