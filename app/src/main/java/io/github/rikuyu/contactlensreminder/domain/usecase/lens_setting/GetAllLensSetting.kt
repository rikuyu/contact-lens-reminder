package io.github.rikuyu.contactlensreminder.domain.usecase.lens_setting

import io.github.rikuyu.contactlensreminder.domain.model.LensSettingValue
import io.github.rikuyu.contactlensreminder.domain.repository.LensSettingRepository
import javax.inject.Inject

class GetAllLensSetting @Inject constructor (
    private val repository: LensSettingRepository
) {
    operator fun invoke(): LensSettingValue {
        return repository.getAllLensSetting()
    }
}