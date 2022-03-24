package io.github.rikuyu.contactlensreminder.domain.usecase.app_setting

import com.google.common.truth.Truth.assertThat
import io.github.rikuyu.contactlensreminder.domain.repository.AppSettingRepository
import io.github.rikuyu.contactlensreminder.ui.theme.ThemeColor
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class AppSettingUseCaseTest {

    lateinit var logEvent: LogEvent
    lateinit var getThemeColor: GetThemeColor
    lateinit var saveThemeColor: SaveThemeColor
    lateinit var appSettingUseCase: AppSettingUseCase

    @MockK
    lateinit var repository: AppSettingRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        logEvent = LogEvent(repository)
        getThemeColor = GetThemeColor(repository)
        saveThemeColor = SaveThemeColor(repository)
        appSettingUseCase = AppSettingUseCase(logEvent, getThemeColor, saveThemeColor)
    }

    @Test
    fun `LogEvent Test`() {
        every { repository.logEvent(any()) } returns Unit
        appSettingUseCase.logEvent.invoke("")
        verify(exactly = 1) {
            repository.logEvent(any())
            appSettingUseCase.logEvent.invoke("")
        }
    }

    @Test
    fun `GetThemeColor Test`() {
        every { repository.getThemeColor() } returns ThemeColor.Blue.name.lowercase()

        assertThat(appSettingUseCase.getThemeColor.invoke()).isEqualTo(ThemeColor.Blue.name.lowercase())

        verify(exactly = 1) {
            repository.getThemeColor()
            appSettingUseCase.getThemeColor.invoke()
        }
    }

    @Test
    fun `SaveThemeColor Test`() {
        every { repository.saveThemeColor(ThemeColor.Purple.name.lowercase()) } returns Unit
        appSettingUseCase.saveThemeColor.invoke(ThemeColor.Purple.name.lowercase())

        verify(exactly = 1) {
            repository.saveThemeColor(ThemeColor.Purple.name.lowercase())
            appSettingUseCase.saveThemeColor.invoke(ThemeColor.Purple.name.lowercase())
        }
    }
}