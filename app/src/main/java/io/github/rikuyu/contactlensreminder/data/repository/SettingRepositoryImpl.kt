package io.github.rikuyu.contactlensreminder.data.repository

import io.github.rikuyu.contactlensreminder.data.local.LocalDataSource
import io.github.rikuyu.contactlensreminder.domain.SettingValue
import io.github.rikuyu.contactlensreminder.domain.repository.SettingRepository

class SettingRepositoryImpl(private val localDataSource: LocalDataSource) :
    SettingRepository {

    override fun saveAllSetting(settingValue: SettingValue) {
        localDataSource.saveAllSetting(settingValue)
    }

    override fun getAllSetting(): SettingValue = localDataSource.getAllSetting()
}