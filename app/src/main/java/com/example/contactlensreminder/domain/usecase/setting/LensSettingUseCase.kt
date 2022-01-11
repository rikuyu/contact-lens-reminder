package com.example.contactlensreminder.domain.usecase.setting

data class LensSettingUseCase(
    val setAllSetting: SetAllSetting,
    val getAllSetting: GetAllSetting,
    val saveLensPower: SaveLensPower
)