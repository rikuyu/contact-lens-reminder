package com.example.contactlensreminder.presentation

import androidx.lifecycle.ViewModel
import com.example.contactlensreminder.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    fun setLensPower() {
        repository.setLensPower()
    }

    fun setReminder() {
        repository.setReminder()
    }
}