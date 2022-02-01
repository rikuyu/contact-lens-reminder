package io.github.rikuyu.contactlensreminder.domain.usecase.reminder

data class ReminderUseCase(
    val saveReminderSetting: SaveReminderSetting,
    val startReminder: StartReminder,
    val getReminderSetting: GetReminderSetting,
    val cancelReminder: CancelReminder
)