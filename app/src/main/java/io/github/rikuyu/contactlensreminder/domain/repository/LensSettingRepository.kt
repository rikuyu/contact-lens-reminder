package io.github.rikuyu.contactlensreminder.domain.repository

import io.github.rikuyu.contactlensreminder.domain.model.LensSettingValue

interface LensSettingRepository {

    fun saveAllLensSetting(lensSettingValue: LensSettingValue)

    fun getAllLensSetting(): LensSettingValue

    fun createChannel()
}