package io.github.rikuyu.contactlensreminder.domain.repository

import io.github.rikuyu.contactlensreminder.domain.model.SettingValue

interface SettingRepository {

    fun saveAllSetting(settingValue: SettingValue)

    fun getAllSetting(): SettingValue
}