package com.example.contactlensreminder.domain.usecase.setting

import com.example.contactlensreminder.domain.repository.SettingRepository
import com.example.contactlensreminder.presentation.screens.lens_setting.SettingValue

class GetAllSetting(
    private val repository: SettingRepository
) {
    operator fun invoke(): SettingValue {
        return repository.getAllSetting()
    }
}