package com.example.contactlensreminder.domain.usecase.setting

import com.example.contactlensreminder.domain.repository.MainRepository
import com.example.contactlensreminder.presentation.SettingValue

class GetAllSetting(
    private val repository: MainRepository
) {
    operator fun invoke(): SettingValue {
        return repository.getAllSetting()
    }
}