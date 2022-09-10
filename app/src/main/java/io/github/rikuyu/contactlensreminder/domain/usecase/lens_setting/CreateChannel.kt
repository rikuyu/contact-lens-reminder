package io.github.rikuyu.contactlensreminder.domain.usecase.lens_setting

import io.github.rikuyu.contactlensreminder.domain.repository.LensSettingRepository
import javax.inject.Inject

class CreateChannel @Inject constructor(
    private val repository: LensSettingRepository
) {
    operator fun invoke() = repository.createChannel()
}