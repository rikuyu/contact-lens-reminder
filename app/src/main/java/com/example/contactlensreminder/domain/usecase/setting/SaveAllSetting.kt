package com.example.contactlensreminder.domain.usecase.setting

import com.example.contactlensreminder.domain.repository.SettingRepository
import com.example.contactlensreminder.presentation.screens.lens_setting.SettingValue

class SaveAllSetting(
    private val repository: SettingRepository
) {
    operator fun invoke(setting: SettingValue) {
        repository.saveAllSetting(setting)
    }
}