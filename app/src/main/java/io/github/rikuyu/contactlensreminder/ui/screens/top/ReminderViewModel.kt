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
    private val useCase: ReminderUseCase
) : ViewModel() {

    private val _reminder: MutableState<ReminderValue> = mutableStateOf(ReminderValue())
    val reminder: State<ReminderValue> = _reminder

    private val _isShowOnBoarding: MutableState<Boolean> = mutableStateOf(true)
    val isShowOnBoarding: State<Boolean> = _isShowOnBoarding

    init {
        _reminder.value = useCase.getReminderSetting.invoke()
        _isShowOnBoarding.value = useCase.getIsShowOnBoarding.invoke()
    }

    fun onEvent(event: ReminderEvent) {
        _reminder.value = reminder.value.copy(
            isUsingContactLens = event.reminderValue.isUsingContactLens,
            lensRemainingDays = event.reminderValue.lensPeriod
        )
        useCase.saveReminderSetting(reminder.value)
        when (event) {
            is ReminderEvent.StartReminder -> useCase.startReminder()
            is ReminderEvent.CancelReminder -> useCase.cancelReminder()
        }
    }
}