package com.example.contactlensreminder.domain.usecase.setting

import com.example.contactlensreminder.domain.repository.SettingRepository
import com.example.contactlensreminder.domain.SettingValue

class GetAllSetting(
    private val repository: SettingRepository
) {
    operator fun invoke(): SettingValue {
        return repository.getAllSetting()
    }
}