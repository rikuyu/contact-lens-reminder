package io.github.rikuyu.contactlensreminder.ui.screens.lens_setting

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.rikuyu.contactlensreminder.domain.model.LensSettingValue
import io.github.rikuyu.contactlensreminder.domain.usecase.lens_setting.LensSettingUseCase
import javax.inject.Inject

@HiltViewModel
class LensSettingViewModel @Inject constructor(
    private val lensSettingUseCase: LensSettingUseCase
) : ViewModel() {

    private val _Lens_setting: MutableState<LensSettingValue> = mutableStateOf(LensSettingValue())
    val lensSetting: State<LensSettingValue> = _Lens_setting

    init {
        _Lens_setting.value = lensSettingUseCase.getAllLensSetting.invoke()
    }

    fun onEvent(eventLens: LensSettingEvent) {
        when (eventLens) {
            is LensSettingEvent.LensType -> {
                _Lens_setting.value = lensSetting.value.copy(
                    lensType = eventLens.lensType
                )
            }
            is LensSettingEvent.LensPeriod -> {
                _Lens_setting.value = lensSetting.value.copy(
                    lensPeriod = eventLens.lensPeriod
                )
            }
            is LensSettingEvent.IsUseNotification -> {
                _Lens_setting.value = lensSetting.value.copy(
                    isUseNotification = eventLens.isUseNotification
                )
            }
            is LensSettingEvent.NotificationDay -> {
                _Lens_setting.value = lensSetting.value.copy(
                    notificationDay = eventLens.notificationType
                )
            }
            is LensSettingEvent.NotificationTimeHour -> {
                _Lens_setting.value = lensSetting.value.copy(
                    notificationTimeHour = eventLens.notificationTimeHour
                )
            }
            is LensSettingEvent.NotificationTimeMinute -> {
                _Lens_setting.value = lensSetting.value.copy(
                    notificationTimeMinute = eventLens.notificationTimeMinute
                )
            }
            is LensSettingEvent.IsShowLensPowerSection -> {
                _Lens_setting.value = lensSetting.value.copy(
                    isShowLensPowerSection = eventLens.isShowLensPowerSection
                )
            }
            is LensSettingEvent.LeftPower -> {
                _Lens_setting.value = lensSetting.value.copy(
                    leftLensPower = eventLens.leftLensPower
                )
            }
            is LensSettingEvent.RightPower -> {
                _Lens_setting.value = lensSetting.value.copy(
                    rightLensPower = eventLens.rightLensPower
                )
            }
            is LensSettingEvent.SaveLensSetting -> lensSettingUseCase.saveAllLensSetting(lensSetting.value)
        }
    }
}