package com.example.contactlensreminder.domain.usecase.setting

import com.example.contactlensreminder.domain.repository.MainRepository

class SaveLensPower(
    private val repository: MainRepository
) {
    operator fun invoke(leftLensPower: Double, rightLensPower: Double) {
        repository.saveLensPower(leftLensPower, rightLensPower)
    }
}