package io.github.rikuyu.contactlensreminder.ui.screens.app_setting

import io.github.rikuyu.contactlensreminder.ui.theme.ThemeColor

sealed class AppSettingEvent {
    data class LogEvent(val label: String) : AppSettingEvent()

    data class SaveThemeColor(val color: ThemeColor) : AppSettingEvent()

    object GetThemeColor : AppSettingEvent()
}