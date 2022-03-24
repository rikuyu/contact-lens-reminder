package io.github.rikuyu.contactlensreminder.domain.usecase.app_setting

import javax.inject.Inject

data class AppSettingUseCase @Inject constructor(
    val logEvent: LogEvent,
    val getThemeColor: GetThemeColor,
    val saveThemeColor: SaveThemeColor
)