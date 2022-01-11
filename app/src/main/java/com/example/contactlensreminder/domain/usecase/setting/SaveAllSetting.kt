package com.example.contactlensreminder.domain.usecase.setting

import com.example.contactlensreminder.domain.repository.MainRepository
import com.example.contactlensreminder.presentation.SettingValue

class SaveAllSetting(
    private val repository: MainRepository
) {
    operator fun invoke(setting: SettingValue) {
        repository.setAllSetting(setting)
    }
}