package io.github.rikuyu.contactlensreminder.ui.screens.app_setting

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.rikuyu.contactlensreminder.domain.usecase.app_setting.AppSettingUseCase
import javax.inject.Inject

@HiltViewModel
class AppSettingViewModel @Inject constructor(
    private val appSettingUseCase: AppSettingUseCase
) : ViewModel() {

    fun onEvent(event: AppSettingEvent) {
        when (event) {
            is AppSettingEvent.LogEvent -> appSettingUseCase.logEvent.invoke(event.label)
        }
    }
}