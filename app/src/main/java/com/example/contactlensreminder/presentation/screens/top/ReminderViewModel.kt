package com.example.contactlensreminder.presentation.screens.top

import androidx.lifecycle.ViewModel
import com.example.contactlensreminder.domain.usecase.reminder.ReminderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val reminderUseCase: ReminderUseCase
) : ViewModel() {

    fun onEvent(event: ReminderEvent) {
        when (event) {
            ReminderEvent.ReminderStart -> {
            }
            ReminderEvent.ReminderTerminate -> {
            }
        }
    }
}