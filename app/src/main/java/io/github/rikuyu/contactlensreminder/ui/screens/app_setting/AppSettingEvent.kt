package io.github.rikuyu.contactlensreminder.ui.screens.app_setting

sealed class AppSettingEvent {
    data class LogEvent(val label: String) : AppSettingEvent()

    data class SaveThemeColor(val color: String) : AppSettingEvent()

    object GetThemeColor : AppSettingEvent()
}