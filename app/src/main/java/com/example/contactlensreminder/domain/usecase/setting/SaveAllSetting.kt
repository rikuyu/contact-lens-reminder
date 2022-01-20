package com.example.contactlensreminder.domain.usecase.setting

import com.example.contactlensreminder.domain.repository.SettingRepository
import com.example.contactlensreminder.domain.SettingValue

class SaveAllSetting(
    private val repository: SettingRepository
) {
    operator fun invoke(setting: SettingValue) {
        repository.saveAllSetting(setting)
    }
}