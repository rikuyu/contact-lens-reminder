package io.github.rikuyu.contactlensreminder.ui.screens.app_setting

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.rikuyu.contactlensreminder.domain.usecase.app_setting.AppSettingUseCase
import javax.inject.Inject

@HiltViewModel
class AppSettingViewModel @Inject constructor(
    private val appSettingUseCase: AppSettingUseCase,
) : ViewModel() {

    val themeColor: MutableState<String> = mutableStateOf("blue_color")

    init {
        themeColor.value = appSettingUseCase.getThemeColor.invoke()
    }

    fun onEvent(event: AppSettingEvent) {
        when (event) {
            is AppSettingEvent.LogEvent -> appSettingUseCase.logEvent.invoke(event.label)
            is AppSettingEvent.SaveThemeColor -> appSettingUseCase.saveThemeColor.invoke(themeColor.value)
            is AppSettingEvent.GetThemeColor -> themeColor.value = appSettingUseCase.getThemeColor.invoke()
        }
    }
}