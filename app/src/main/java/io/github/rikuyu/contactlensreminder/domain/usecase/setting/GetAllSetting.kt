package io.github.rikuyu.contactlensreminder.domain.usecase.setting

import io.github.rikuyu.contactlensreminder.domain.repository.SettingRepository
import io.github.rikuyu.contactlensreminder.domain.model.SettingValue

class GetAllSetting(
    private val repository: SettingRepository
) {
    operator fun invoke(): SettingValue {
        return repository.getAllSetting()
    }
}