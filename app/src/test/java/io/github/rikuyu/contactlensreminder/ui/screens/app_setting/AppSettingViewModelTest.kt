package io.github.rikuyu.contactlensreminder.ui.screens.app_setting

import io.github.rikuyu.contactlensreminder.domain.usecase.app_setting.AppSettingUseCase
import io.github.rikuyu.contactlensreminder.domain.usecase.app_setting.GetThemeColor
import io.github.rikuyu.contactlensreminder.domain.usecase.app_setting.LogEvent
import io.github.rikuyu.contactlensreminder.domain.usecase.app_setting.SaveThemeColor
import io.github.rikuyu.contactlensreminder.ui.theme.ThemeColor
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class AppSettingViewModelTest {

    private lateinit var viewModel: AppSettingViewModel
    private lateinit var useCase: AppSettingUseCase

    @MockK
    private lateinit var logEvent: LogEvent

    @MockK
    private lateinit var getThemeColor: GetThemeColor

    @MockK
    private lateinit var saveThemeColor: SaveThemeColor

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = AppSettingUseCase(logEvent, getThemeColor, saveThemeColor)
    }

    @Test
    fun `LogEvent が正確に呼び出されているかどうか`() {
        every { logEvent.invoke(any()) } returns Unit
        every { getThemeColor.invoke() } returns ThemeColor.Blue.name.lowercase()

        viewModel = AppSettingViewModel(useCase)
        viewModel.onEvent(AppSettingEvent.LogEvent(""))

        verify(exactly = 1) {
            getThemeColor.invoke()
            viewModel.onEvent(AppSettingEvent.LogEvent(""))
            logEvent.invoke(any())
        }
    }

    @Test
    fun `テーマカラーを変更できるか`() {
        every { getThemeColor.invoke() } returns ThemeColor.Blue.name.lowercase()
        every { saveThemeColor.invoke(ThemeColor.Purple.name.lowercase()) } returns Unit

        viewModel = AppSettingViewModel(useCase)
        viewModel.onEvent(AppSettingEvent.SaveThemeColor(ThemeColor.Purple))

        verify(exactly = 1) {
            getThemeColor.invoke()
            viewModel.onEvent(AppSettingEvent.SaveThemeColor(ThemeColor.Purple))
            saveThemeColor.invoke(ThemeColor.Purple.name.lowercase())
        }
    }

    @Test
    fun `テーマカラーを取得できるか`() {
        every { getThemeColor.invoke() } returns ThemeColor.Blue.name.lowercase()

        viewModel = AppSettingViewModel(useCase)
        viewModel.onEvent(AppSettingEvent.GetThemeColor)

        verify(exactly = 2) { getThemeColor.invoke() }
    }
}