package com.example.contactlensreminder.presentation.screens.top

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.contactlensreminder.domain.usecase.reminder.ReminderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val reminderUseCase: ReminderUseCase
) : ViewModel() {

    private val _reminder: MutableState<ReminderValue> = mutableStateOf(ReminderValue())
    val reminder: State<ReminderValue> = _reminder

    init {
        _reminder.value = reminderUseCase.getReminderSetting.invoke()
    }

    fun onEvent(event: ReminderEvent) {
        when (event) {
            is ReminderEvent.StartReminder -> {
                _reminder.value = reminder.value.copy(
                    isUsingContactLens = event.reminderValue.isUsingContactLens
                )
                reminderUseCase.saveReminderSetting(reminder.value)
                reminderUseCase.startReminder.invoke()
            }
            is ReminderEvent.CancelReminder -> {
                _reminder.value = reminder.value.copy(
                    isUsingContactLens = event.reminderValue.isUsingContactLens
                )
                reminderUseCase.saveReminderSetting(reminder.value)
                reminderUseCase.cancelReminder.invoke()
            }
        }
    }
}