package io.github.rikuyu.contactlensreminder.ui.screens.top

import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue

sealed class ReminderEvent {

    data class StartReminder(val data: ReminderValue) : ReminderEvent()

    data class CancelReminder(val data: ReminderValue) : ReminderEvent()

    object GetReminderSetting : ReminderEvent()

    object GetIsDarkTheme : ReminderEvent()

    object SwitchIsDarkTheme : ReminderEvent()
}