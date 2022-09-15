package io.github.rikuyu.contactlensreminder.data.repository

import io.github.rikuyu.contactlensreminder.domain.local.DataSource
import io.github.rikuyu.contactlensreminder.domain.model.LensSettingValue
import io.github.rikuyu.contactlensreminder.domain.repository.LensSettingRepository
import javax.inject.Inject

class LensSettingRepositoryImpl @Inject constructor(
    private val localDataSource: DataSource
) : LensSettingRepository {

    override fun saveAllLensSetting(lensSettingValue: LensSettingValue) {
        localDataSource.saveAllLensSetting(lensSettingValue)
    }

    override fun getAllLensSetting(): LensSettingValue = localDataSource.getAllLensSetting()

    override fun createChannel() {
        localDataSource.createChannel()
    }
}