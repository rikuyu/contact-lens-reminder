package com.example.contactlensreminder.data.repository

import com.example.contactlensreminder.data.local.LocalDataSource
import com.example.contactlensreminder.domain.SettingValue
import com.example.contactlensreminder.domain.repository.SettingRepository

class SettingRepositoryImpl(private val localDataSource: LocalDataSource) :
    SettingRepository {

    override fun saveAllSetting(settingValue: SettingValue) {
        localDataSource.saveAllSetting(settingValue)
    }

    override fun getAllSetting(): SettingValue = localDataSource.getAllSetting()
}