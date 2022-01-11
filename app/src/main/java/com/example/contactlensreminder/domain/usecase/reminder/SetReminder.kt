package com.example.contactlensreminder.domain.usecase.reminder

import com.example.contactlensreminder.domain.repository.ReminderRepository

class SetReminder(
    private val repository: ReminderRepository
)