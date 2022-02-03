package io.github.rikuyu.contactlensreminder.domain.usecase.setting

import io.github.rikuyu.contactlensreminder.domain.model.SettingValue
import io.github.rikuyu.contactlensreminder.domain.repository.SettingRepository
import javax.inject.Inject

class GetAllSetting @Inject constructor (
    private val repository: SettingRepository
) {
    operator fun invoke(): SettingValue {
        return repository.getAllSetting()
    }
}