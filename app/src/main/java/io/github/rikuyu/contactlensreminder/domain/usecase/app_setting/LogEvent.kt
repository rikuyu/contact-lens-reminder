package io.github.rikuyu.contactlensreminder.domain.usecase.app_setting

import io.github.rikuyu.contactlensreminder.domain.repository.AppSettingRepository
import javax.inject.Inject

class LogEvent @Inject constructor(
    private val repository: AppSettingRepository
) {
    operator fun invoke(label: String) {
        repository.logEvent(label)
    }
}