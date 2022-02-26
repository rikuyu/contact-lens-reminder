package io.github.rikuyu.contactlensreminder.data.repository

import io.github.rikuyu.contactlensreminder.domain.local.DataSource
import io.github.rikuyu.contactlensreminder.domain.repository.AppSettingRepository
import javax.inject.Inject

class AppSettingRepositoryImpl @Inject constructor(
    private val localDataSource: DataSource
) : AppSettingRepository {

    override fun logEvent(label: String) {
        localDataSource.logEvent(label)
    }
}