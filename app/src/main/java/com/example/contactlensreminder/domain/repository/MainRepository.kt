package com.example.contactlensreminder.domain.repository

import com.example.contactlensreminder.presentation.SettingValue

interface MainRepository {

    fun saveLensPower(leftLensPower: Double, rightLensPower: Double)

    fun setAllSetting(settingValue: SettingValue)

    fun getAllSetting(): SettingValue

    fun setReminder()
}