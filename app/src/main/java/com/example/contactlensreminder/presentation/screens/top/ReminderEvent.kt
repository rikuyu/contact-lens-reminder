package com.example.contactlensreminder.presentation.screens.top

sealed class ReminderEvent {

    object ReminderStart : ReminderEvent()

    object ReminderTerminate : ReminderEvent()
}