package io.github.rikuyu.contactlensreminder.domain.usecase.setting

import io.github.rikuyu.contactlensreminder.domain.model.SettingValue
import io.github.rikuyu.contactlensreminder.domain.repository.SettingRepository

class GetAllSetting(
    private val repository: SettingRepository
) {
    operator fun invoke(): SettingValue {
        return repository.getAllSetting()
    }
}