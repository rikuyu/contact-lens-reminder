package io.github.rikuyu.contactlensreminder.domain.usecase.lens_setting

import javax.inject.Inject

data class LensSettingUseCase @Inject constructor(

    val saveAllLensSetting: SaveAllLensSetting,

    val getAllLensSetting: GetAllLensSetting,

    val createChannel: CreateChannel
)