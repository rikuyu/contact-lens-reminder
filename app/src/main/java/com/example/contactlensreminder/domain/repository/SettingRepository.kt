package com.example.contactlensreminder.domain.repository

import com.example.contactlensreminder.domain.SettingValue

interface SettingRepository {

    fun saveAllSetting(settingValue: SettingValue)

    fun getAllSetting(): SettingValue
}