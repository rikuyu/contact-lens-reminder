package io.github.rikuyu.contactlensreminder.domain.usecase.reminder

import javax.inject.Inject

data class ReminderUseCase @Inject constructor(

    val saveReminderSetting: SaveReminderSetting,

    val startReminder: StartReminder,

    val getReminderSetting: GetReminderSetting,

    val resetReminder: ResetReminder,

    val getIsShowOnBoarding: GetIsShowOnBoarding,

    val getIsDarkTheme: GetIsDarkTheme,

    val switchIsDarkTheme: SwitchIsDarkTheme
)