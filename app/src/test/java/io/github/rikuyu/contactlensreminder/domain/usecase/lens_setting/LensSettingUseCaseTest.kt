package io.github.rikuyu.contactlensreminder.domain.usecase.lens_setting

import com.google.common.truth.Truth.assertThat
import io.github.rikuyu.contactlensreminder.domain.model.LensSettingValue
import io.github.rikuyu.contactlensreminder.domain.repository.LensSettingRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class LensSettingUseCaseTest {

    lateinit var saveAllLensSetting: SaveAllLensSetting
    lateinit var getAllLensSetting: GetAllLensSetting
    lateinit var lensSettingUseCase: LensSettingUseCase

    @MockK
    lateinit var repository: LensSettingRepository

    private var lensSettingValue: LensSettingValue? = null

    private val expectedSettingValue = LensSettingValue(
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
        saveAllLensSetting = SaveAllLensSetting(repository)
        getAllLensSetting = GetAllLensSetting(repository)
        lensSettingUseCase = LensSettingUseCase(saveAllLensSetting, getAllLensSetting)
    }

    @Test
    fun `GetAllSetting Test`() {
        val dummySettingValue = LensSettingValue(
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
        every { repository.getAllLensSetting() } returns dummySettingValue
        assertThat(lensSettingValue).isNull()
        lensSettingValue = lensSettingUseCase.getAllLensSetting.invoke()
        assertThat(lensSettingValue).isEqualTo(expectedSettingValue)
        verify(exactly = 1) {
            lensSettingUseCase.getAllLensSetting.invoke()
            repository.getAllLensSetting()
        }
    }

    @Test
    fun `SaveAllSetting Test`() {
        every { repository.saveAllLensSetting(any()) } returns Unit
        lensSettingUseCase.saveAllLensSetting.invoke(expectedSettingValue)
        verify(exactly = 1) {
            lensSettingUseCase.saveAllLensSetting.invoke(any())
            repository.saveAllLensSetting(any())
        }
    }
}