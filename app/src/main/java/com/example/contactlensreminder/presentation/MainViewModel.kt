package com.example.contactlensreminder.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.contactlensreminder.domain.usecase.setting.LensSettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: LensSettingUseCase
) : ViewModel() {

    private val _setting: MutableState<SettingValue> = mutableStateOf(SettingValue())
    val setting: State<SettingValue> = _setting

    init {
        _setting.value = useCase.getAllSetting.invoke()
        Log.d("AAAAAAAAAA", "getAllSetting")
    }

    fun onEvent(event: LensEvent) {
        when (event) {
            is LensEvent.LensType -> {
                _setting.value = setting.value.copy(
                    lensType = event.lensType
                )
                Log.d("AAAAAAAAAA", "${event.lensType}")
            }
            is LensEvent.LensPeriod -> {
                _setting.value = setting.value.copy(
                    lensPeriod = event.lensPeriod
                )
                Log.d("AAAAAAAAAA", "${event.lensPeriod}")
            }
            is LensEvent.IsUseNotification -> {
                _setting.value = setting.value.copy(
                    isUseNotification = event.isUseNotification
                )
                Log.d("AAAAAAAAAA", "${event.isUseNotification}")
            }
            is LensEvent.NotificationDay -> {
                _setting.value = setting.value.copy(
                    notificationDay = event.notificationType
                )
                Log.d("AAAAAAAAAA", "${event.notificationType}")
            }
            is LensEvent.LeftLensPower -> {
                _setting.value = setting.value.copy(
                    leftLensPower = event.leftLensPower
                )
                Log.d("AAAAAAAAAA", "${event.leftLensPower}")
            }
            is LensEvent.RightLensPower -> {
                _setting.value = setting.value.copy(
                    rightLensPower = event.rightLensPower
                )
                Log.d("AAAAAAAAAA", "${event.rightLensPower}")
            }
            is LensEvent.SaveLensPower -> {
                useCase.saveLensPower(
                    setting.value.leftLensPower,
                    setting.value.rightLensPower
                )
            }
            is LensEvent.SaveSetting -> {
                useCase.setAllSetting(setting.value)
            }
        }
    }
}