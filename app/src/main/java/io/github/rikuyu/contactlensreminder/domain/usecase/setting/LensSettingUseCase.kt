package io.github.rikuyu.contactlensreminder.domain.usecase.setting

import javax.inject.Inject

data class LensSettingUseCase @Inject constructor (
    val saveAllSetting: SaveAllSetting,
    val getAllSetting: GetAllSetting
)