package io.github.rikuyu.contactlensreminder.domain.usecase.setting

import io.github.rikuyu.contactlensreminder.domain.repository.SettingRepository
import io.github.rikuyu.contactlensreminder.domain.model.SettingValue
import javax.inject.Inject

class SaveAllSetting @Inject constructor (
    private val repository: SettingRepository
) {
    operator fun invoke(setting: SettingValue) {
        repository.saveAllSetting(setting)
    }
}