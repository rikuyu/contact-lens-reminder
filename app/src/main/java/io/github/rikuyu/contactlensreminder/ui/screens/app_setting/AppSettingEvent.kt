package io.github.rikuyu.contactlensreminder.ui.screens.app_setting

sealed class AppSettingEvent {
    data class LogEvent(val label: String) : AppSettingEvent()
}