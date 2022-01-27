package io.github.rikuyu.contactlensreminder.presentation.screens.top

import io.github.rikuyu.contactlensreminder.domain.ReminderValue

sealed class ReminderEvent(val reminderValue: ReminderValue) {

    data class StartReminder(
        val data: ReminderValue
    ) : ReminderEvent(data)

    data class CancelReminder(
        val data: ReminderValue
    ) : ReminderEvent(data)
}