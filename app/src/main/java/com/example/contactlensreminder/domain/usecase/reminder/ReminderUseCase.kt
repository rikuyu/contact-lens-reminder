package com.example.contactlensreminder.domain.usecase.reminder

data class ReminderUseCase(
    val setNotificationData: SetNotificationData,
    val setReminder: SetReminder,
    val getReminderSetting: GetReminderSetting
)