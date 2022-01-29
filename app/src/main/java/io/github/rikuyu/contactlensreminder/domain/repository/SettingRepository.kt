package io.github.rikuyu.contactlensreminder.domain.repository

import io.github.rikuyu.contactlensreminder.domain.SettingValue

interface SettingRepository {

    fun saveAllSetting(settingValue: SettingValue)

    fun getAllSetting(): SettingValue
}