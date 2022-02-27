package io.github.rikuyu.contactlensreminder.ui.screens.app_setting

import io.github.rikuyu.contactlensreminder.domain.usecase.app_setting.AppSettingUseCase
import io.github.rikuyu.contactlensreminder.domain.usecase.app_setting.LogEvent
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

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = AppSettingUseCase(logEvent)
    }

    @Test
    fun `LogEvent が正確に呼び出されているかどうか`() {
        every { logEvent.invoke(any()) } returns Unit
        viewModel = AppSettingViewModel(useCase)
        viewModel.onEvent(AppSettingEvent.LogEvent(""))
        verify(exactly = 1) { logEvent.invoke(any()) }
    }
}