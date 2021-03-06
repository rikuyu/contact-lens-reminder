package io.github.rikuyu.contactlensreminder.domain.usecase.lens_setting

import io.github.rikuyu.contactlensreminder.domain.repository.LensSettingRepository
import io.github.rikuyu.contactlensreminder.domain.model.LensSettingValue
import javax.inject.Inject

class SaveAllLensSetting @Inject constructor(
    private val repository: LensSettingRepository
) {
    operator fun invoke(lensSetting: LensSettingValue) {
        repository.saveAllLensSetting(lensSetting)
    }
}