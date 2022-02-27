package io.github.rikuyu.contactlensreminder.ui.screens.lens_setting

import com.google.common.truth.Truth.assertThat
import io.github.rikuyu.contactlensreminder.domain.model.LensSettingValue
import io.github.rikuyu.contactlensreminder.domain.usecase.lens_setting.GetAllLensSetting
import io.github.rikuyu.contactlensreminder.domain.usecase.lens_setting.LensSettingUseCase
import io.github.rikuyu.contactlensreminder.domain.usecase.lens_setting.SaveAllLensSetting
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class LensSettingViewModelTest {

    lateinit var viewModelLens: LensSettingViewModel
    lateinit var useCase: LensSettingUseCase

    @MockK
    lateinit var saveAllLensSetting: SaveAllLensSetting

    @MockK
    lateinit var getAllLensSetting: GetAllLensSetting

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = LensSettingUseCase(saveAllLensSetting, getAllLensSetting)
    }

    private val defaultSettingValue = LensSettingValue()

    @Test
    fun `onEvent SettingEvent LensType `() {
        every { getAllLensSetting.invoke() } returns defaultSettingValue
        viewModelLens = LensSettingViewModel(useCase)

        val expected = defaultSettingValue.copy(lensType = 1)

        assertThat(viewModelLens.lensSetting.value).isEqualTo(defaultSettingValue)
        viewModelLens.onEvent(LensSettingEvent.LensType(1))
        assertThat(viewModelLens.lensSetting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllLensSetting.invoke() }
    }

    @Test
    fun `onEvent SettingEvent LensPeriod `() {
        every { getAllLensSetting.invoke() } returns defaultSettingValue
        viewModelLens = LensSettingViewModel(useCase)

        val expected = defaultSettingValue.copy(lensPeriod = 20)

        assertThat(viewModelLens.lensSetting.value).isEqualTo(defaultSettingValue)
        viewModelLens.onEvent(LensSettingEvent.LensPeriod(20))
        assertThat(viewModelLens.lensSetting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllLensSetting.invoke() }
    }

    @Test
    fun `onEvent SettingEvent IsUseNotification `() {
        every { getAllLensSetting.invoke() } returns defaultSettingValue
        viewModelLens = LensSettingViewModel(useCase)

        val expected = defaultSettingValue.copy(isUseNotification = false)

        assertThat(viewModelLens.lensSetting.value).isEqualTo(defaultSettingValue)
        viewModelLens.onEvent(LensSettingEvent.IsUseNotification(false))
        assertThat(viewModelLens.lensSetting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllLensSetting.invoke() }
    }

    @Test
    fun `onEvent SettingEvent NotificationDay `() {
        every { getAllLensSetting.invoke() } returns defaultSettingValue
        viewModelLens = LensSettingViewModel(useCase)

        val expected = defaultSettingValue.copy(notificationDay = 1)

        assertThat(viewModelLens.lensSetting.value).isEqualTo(defaultSettingValue)
        viewModelLens.onEvent(LensSettingEvent.NotificationDay(1))
        assertThat(viewModelLens.lensSetting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllLensSetting.invoke() }
    }

    @Test
    fun `onEvent SettingEvent NotificationTimeHour `() {
        every { getAllLensSetting.invoke() } returns defaultSettingValue
        viewModelLens = LensSettingViewModel(useCase)

        val expected = defaultSettingValue.copy(notificationTimeHour = 9)

        assertThat(viewModelLens.lensSetting.value).isEqualTo(defaultSettingValue)
        viewModelLens.onEvent(LensSettingEvent.NotificationTimeHour(9))
        assertThat(viewModelLens.lensSetting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllLensSetting.invoke() }
    }

    @Test
    fun `onEvent SettingEvent NotificationTimeMinute `() {
        every { getAllLensSetting.invoke() } returns defaultSettingValue
        viewModelLens = LensSettingViewModel(useCase)

        val expected = defaultSettingValue.copy(notificationTimeMinute = 45)

        assertThat(viewModelLens.lensSetting.value).isEqualTo(defaultSettingValue)
        viewModelLens.onEvent(LensSettingEvent.NotificationTimeMinute(45))
        assertThat(viewModelLens.lensSetting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllLensSetting.invoke() }
    }

    @Test
    fun `onEvent SettingEvent IsShowLensPowerSection `() {
        every { getAllLensSetting.invoke() } returns defaultSettingValue
        viewModelLens = LensSettingViewModel(useCase)

        val expected = defaultSettingValue.copy(isShowLensPowerSection = true)

        assertThat(viewModelLens.lensSetting.value).isEqualTo(defaultSettingValue)
        viewModelLens.onEvent(LensSettingEvent.IsShowLensPowerSection(true))
        assertThat(viewModelLens.lensSetting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllLensSetting.invoke() }
    }

    @Test
    fun `onEvent SettingEvent LeftPower `() {
        every { getAllLensSetting.invoke() } returns defaultSettingValue
        viewModelLens = LensSettingViewModel(useCase)

        val expected = defaultSettingValue.copy(leftLensPower = "-4.75")

        assertThat(viewModelLens.lensSetting.value).isEqualTo(defaultSettingValue)
        viewModelLens.onEvent(LensSettingEvent.LeftPower("-4.75"))
        assertThat(viewModelLens.lensSetting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllLensSetting.invoke() }
    }

    @Test
    fun `onEvent SettingEvent RightPower `() {
        every { getAllLensSetting.invoke() } returns defaultSettingValue
        viewModelLens = LensSettingViewModel(useCase)

        val expected = defaultSettingValue.copy(rightLensPower = "5.00")

        assertThat(viewModelLens.lensSetting.value).isEqualTo(defaultSettingValue)
        viewModelLens.onEvent(LensSettingEvent.RightPower("5.00"))
        assertThat(viewModelLens.lensSetting.value).isEqualTo(expected)

        verify(exactly = 1) { getAllLensSetting.invoke() }
    }
}