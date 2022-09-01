package io.github.rikuyu.contactlensreminder.ui.screens.top

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue
import io.github.rikuyu.contactlensreminder.domain.usecase.reminder.ReminderUseCase
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val usecase: ReminderUseCase,
) : ViewModel() {

    private val _reminder: MutableState<ReminderValue> = mutableStateOf(ReminderValue())
    val reminder: State<ReminderValue> = _reminder

    private val _isShowOnBoarding: MutableState<Boolean> = mutableStateOf(false)
    val isShowOnBoarding: State<Boolean> = _isShowOnBoarding

    val isDarkTheme: MutableState<Boolean> = mutableStateOf(false)

    init {
        _reminder.value = usecase.getReminderSetting.invoke()
        _isShowOnBoarding.value = usecase.getIsShowOnBoarding.invoke()
        isDarkTheme.value = usecase.getIsDarkTheme.invoke()
        usecase.createChannel.invoke()
    }

    fun onEvent(event: ReminderEvent) {
        when (event) {
            is ReminderEvent.StartReminder -> {
                _reminder.value = reminder.value.copy(
                    isUsingContactLens = event.data.isUsingContactLens,
                    lensRemainingDays = event.data.lensPeriod
                )
                usecase.saveReminderSetting(reminder.value)
                usecase.startReminder()
            }
            is ReminderEvent.ResetReminder -> {
                _reminder.value = reminder.value.copy(
                    isUsingContactLens = event.data.isUsingContactLens,
                    lensRemainingDays = event.data.lensPeriod
                )
                usecase.saveReminderSetting(reminder.value)
                usecase.resetReminder()
            }
            is ReminderEvent.GetReminderSetting -> _reminder.value = usecase.getReminderSetting.invoke()
            is ReminderEvent.SwitchIsDarkTheme -> usecase.switchIsDarkTheme.invoke()
            is ReminderEvent.GetIsDarkTheme -> isDarkTheme.value = usecase.getIsDarkTheme.invoke()
        }
    }
}