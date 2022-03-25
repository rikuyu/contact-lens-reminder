package io.github.rikuyu.contactlensreminder.domain.repository

interface AppSettingRepository {

    fun logEvent(label: String)

    fun getThemeColor(): String

    fun saveThemeColor(color: String)
}