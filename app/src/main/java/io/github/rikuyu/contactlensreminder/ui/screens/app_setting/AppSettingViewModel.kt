package io.github.rikuyu.contactlensreminder.ui.screens.app_setting

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.rikuyu.contactlensreminder.domain.usecase.app_setting.AppSettingUseCase
import io.github.rikuyu.contactlensreminder.ui.theme.ThemeColor
import javax.inject.Inject

@HiltViewModel
class AppSettingViewModel @Inject constructor(
    private val usecase: AppSettingUseCase,
) : ViewModel() {

    val themeColor: MutableState<ThemeColor> = mutableStateOf(ThemeColor.Blue)

    init {
        themeColor.value = ThemeColor.convertToEnumFromString(usecase.getThemeColor.invoke())
    }

    fun onEvent(event: AppSettingEvent) {
        when (event) {
            is AppSettingEvent.LogEvent -> usecase.logEvent.invoke(event.label)
            is AppSettingEvent.SaveThemeColor -> usecase.saveThemeColor.invoke(event.color.name.lowercase())
            is AppSettingEvent.GetThemeColor ->
                themeColor.value = ThemeColor.convertToEnumFromString(usecase.getThemeColor.invoke())
        }
    }
}