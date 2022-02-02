package io.github.rikuyu.contactlensreminder.data.repository

import io.github.rikuyu.contactlensreminder.domain.local.DataSource
import io.github.rikuyu.contactlensreminder.domain.model.SettingValue
import io.github.rikuyu.contactlensreminder.domain.repository.SettingRepository
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(private val localDataSource: DataSource) :
    SettingRepository {

    override fun saveAllSetting(settingValue: SettingValue) {
        localDataSource.saveAllSetting(settingValue)
    }

    override fun getAllSetting(): SettingValue = localDataSource.getAllSetting()
}