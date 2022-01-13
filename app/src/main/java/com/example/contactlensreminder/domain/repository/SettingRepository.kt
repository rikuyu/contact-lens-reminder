package com.example.contactlensreminder.domain.repository

import com.example.contactlensreminder.presentation.screens.lens_setting.SettingValue

interface SettingRepository {

    fun saveAllSetting(settingValue: SettingValue)

    fun getAllSetting(): SettingValue
}