package io.github.rikuyu.contactlensreminder.ui.screens.lens_setting

import com.google.common.truth.Truth.assertThat
import io.github.rikuyu.contactlensreminder.domain.model.SettingValue
import io.github.rikuyu.contactlensreminder.domain.usecase.setting.GetAllSetting
import io.github.rikuyu.contactlensreminder.domain.usecase.setting.LensSettingUseCase
import io.github.rikuyu.contactlensreminder.domain.usecase.setting.SaveAllSetting
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SettingViewModelTest {

    lateinit var viewModel: SettingViewModel
    lateinit var useCase: LensSettingUseCase

    @MockK
    lateinit var saveAllSetting: SaveAllSetting

    @MockK
    lateinit var getAllSetting: GetAllSetting

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = LensSettingUseCase(saveAllSetting, getAllSetting)
    }

    private val defaultSettingValue = SettingValue()

    @Test
    fun `onEvent SettingEvent LensType `() {
        every { getAllSetting.invoke() } returns defaultSettingValue
        viewModel = SettingViewModel(useCase)

        val expected = defaultSettingValue.copy(lensType = 1)

        assertThat(viewModel.setting.value).isEqualTo(defaultSettingValue)
        viewModel.onEvent(SettingEvent.LensType(1))
        assertThat(viewModel.setting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllSetting.invoke() }
    }

    @Test
    fun `onEvent SettingEvent LensPeriod `() {
        every { getAllSetting.invoke() } returns defaultSettingValue
        viewModel = SettingViewModel(useCase)

        val expected = defaultSettingValue.copy(lensPeriod = 20)

        assertThat(viewModel.setting.value).isEqualTo(defaultSettingValue)
        viewModel.onEvent(SettingEvent.LensPeriod(20))
        assertThat(viewModel.setting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllSetting.invoke() }
    }

    @Test
    fun `onEvent SettingEvent IsUseNotification `() {
        every { getAllSetting.invoke() } returns defaultSettingValue
        viewModel = SettingViewModel(useCase)

        val expected = defaultSettingValue.copy(isUseNotification = false)

        assertThat(viewModel.setting.value).isEqualTo(defaultSettingValue)
        viewModel.onEvent(SettingEvent.IsUseNotification(false))
        assertThat(viewModel.setting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllSetting.invoke() }
    }

    @Test
    fun `onEvent SettingEvent NotificationDay `() {
        every { getAllSetting.invoke() } returns defaultSettingValue
        viewModel = SettingViewModel(useCase)

        val expected = defaultSettingValue.copy(notificationDay = 1)

        assertThat(viewModel.setting.value).isEqualTo(defaultSettingValue)
        viewModel.onEvent(SettingEvent.NotificationDay(1))
        assertThat(viewModel.setting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllSetting.invoke() }
    }

    @Test
    fun `onEvent SettingEvent NotificationTimeHour `() {
        every { getAllSetting.invoke() } returns defaultSettingValue
        viewModel = SettingViewModel(useCase)

        val expected = defaultSettingValue.copy(notificationTimeHour = 9)

        assertThat(viewModel.setting.value).isEqualTo(defaultSettingValue)
        viewModel.onEvent(SettingEvent.NotificationTimeHour(9))
        assertThat(viewModel.setting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllSetting.invoke() }
    }

    @Test
    fun `onEvent SettingEvent NotificationTimeMinute `() {
        every { getAllSetting.invoke() } returns defaultSettingValue
        viewModel = SettingViewModel(useCase)

        val expected = defaultSettingValue.copy(notificationTimeMinute = 45)

        assertThat(viewModel.setting.value).isEqualTo(defaultSettingValue)
        viewModel.onEvent(SettingEvent.NotificationTimeMinute(45))
        assertThat(viewModel.setting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllSetting.invoke() }
    }

    @Test
    fun `onEvent SettingEvent IsShowLensPowerSection `() {
        every { getAllSetting.invoke() } returns defaultSettingValue
        viewModel = SettingViewModel(useCase)

        val expected = defaultSettingValue.copy(isShowLensPowerSection = true)

        assertThat(viewModel.setting.value).isEqualTo(defaultSettingValue)
        viewModel.onEvent(SettingEvent.IsShowLensPowerSection(true))
        assertThat(viewModel.setting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllSetting.invoke() }
    }

    @Test
    fun `onEvent SettingEvent LeftPower `() {
        every { getAllSetting.invoke() } returns defaultSettingValue
        viewModel = SettingViewModel(useCase)

        val expected = defaultSettingValue.copy(leftLensPower = "-4.75")

        assertThat(viewModel.setting.value).isEqualTo(defaultSettingValue)
        viewModel.onEvent(SettingEvent.LeftPower("-4.75"))
        assertThat(viewModel.setting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllSetting.invoke() }
    }

    @Test
    fun `onEvent SettingEvent RightPower `() {
        every { getAllSetting.invoke() } returns defaultSettingValue
        viewModel = SettingViewModel(useCase)

        val expected = defaultSettingValue.copy(rightLensPower = "5.00")

        assertThat(viewModel.setting.value).isEqualTo(defaultSettingValue)
        viewModel.onEvent(SettingEvent.RightPower("5.00"))
        assertThat(viewModel.setting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllSetting.invoke() }
    }
}