package io.github.rikuyu.contactlensreminder.data.repository

import com.google.common.truth.Truth.assertThat
import io.github.rikuyu.contactlensreminder.domain.local.DataSource
import io.github.rikuyu.contactlensreminder.domain.model.LensSettingValue
import io.github.rikuyu.contactlensreminder.domain.repository.LensSettingRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class LensSettingRepositoryTest {

    lateinit var lensSettingRepository: LensSettingRepository

    @MockK
    lateinit var localDataSource: DataSource

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
        lensSettingRepository = LensSettingRepositoryImpl(localDataSource)
    }

    @Test
    fun `getAllSetting test`() {
        val dummySetting = LensSettingValue(
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
        every { localDataSource.getAllLensSetting() } returns dummySetting
        assertThat(lensSettingValue).isNull()
        lensSettingValue = lensSettingRepository.getAllLensSetting()
        assertThat(lensSettingValue).isEqualTo(expectedSettingValue)
    }

    @Test
    fun `saveAllSetting test`() {
        every { localDataSource.saveAllLensSetting(any()) } returns Unit
        lensSettingRepository.saveAllLensSetting(expectedSettingValue)
        verify(exactly = 1) {
            localDataSource.saveAllLensSetting(any())
            lensSettingRepository.saveAllLensSetting(any())
        }
    }
}