package io.github.rikuyu.contactlensreminder.domain.usecase.setting

import io.github.rikuyu.contactlensreminder.domain.repository.SettingRepository
import io.github.rikuyu.contactlensreminder.domain.model.SettingValue

class SaveAllSetting(
    private val repository: SettingRepository
) {
    operator fun invoke(setting: SettingValue) {
        repository.saveAllSetting(setting)
    }
}