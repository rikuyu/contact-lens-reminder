package io.github.rikuyu.contactlensreminder.domain.usecase.setting

import com.google.common.truth.Truth.assertThat
import io.github.rikuyu.contactlensreminder.domain.model.SettingValue
import io.github.rikuyu.contactlensreminder.domain.repository.SettingRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class LensSettingUseCaseTest {

    lateinit var saveAllSetting: SaveAllSetting
    lateinit var getAllSetting: GetAllSetting
    lateinit var lensSettingUseCase: LensSettingUseCase

    @MockK
    lateinit var repository: SettingRepository

    private var settingValue: SettingValue? = null

    private val expectedSettingValue = SettingValue(
        lensType = 2,
        lensPeriod = 31,
        isUseNotification = true,
        notificationDay = 1,
        notificationTimeHour = 12,
        notificationTimeMinute = 48,
        isShowLensPowerSection = false,
        leftLensPower = "-4.75",
        rightLensPower = "-5.00"
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        saveAllSetting = SaveAllSetting(repository)
        getAllSetting = GetAllSetting(repository)
        lensSettingUseCase = LensSettingUseCase(saveAllSetting, getAllSetting)
    }

    @Test
    fun `GetAllSetting Test`() {
        val dummySettingValue = SettingValue(
            lensType = 2,
            lensPeriod = 31,
            isUseNotification = true,
            notificationDay = 1,
            notificationTimeHour = 12,
            notificationTimeMinute = 48,
            isShowLensPowerSection = false,
            leftLensPower = "-4.75",
            rightLensPower = "-5.00"
        )
        every { repository.getAllSetting() } returns dummySettingValue
        assertThat(settingValue).isNull()
        settingValue = lensSettingUseCase.getAllSetting.invoke()
        assertThat(settingValue).isEqualTo(expectedSettingValue)
        verify(exactly = 1) {
            lensSettingUseCase.getAllSetting.invoke()
            repository.getAllSetting()
        }
    }

    @Test
    fun `SaveAllSetting Test`() {
        every { repository.saveAllSetting(any()) } returns Unit
        lensSettingUseCase.saveAllSetting.invoke(expectedSettingValue)
        verify(exactly = 1) {
            lensSettingUseCase.saveAllSetting.invoke(any())
            repository.saveAllSetting(any())
        }
    }
}