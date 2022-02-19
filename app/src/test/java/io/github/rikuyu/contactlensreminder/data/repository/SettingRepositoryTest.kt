package io.github.rikuyu.contactlensreminder.data.repository

import com.google.common.truth.Truth.assertThat
import io.github.rikuyu.contactlensreminder.domain.local.DataSource
import io.github.rikuyu.contactlensreminder.domain.model.SettingValue
import io.github.rikuyu.contactlensreminder.domain.repository.SettingRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SettingRepositoryTest {

    lateinit var settingRepository: SettingRepository

    @MockK
    lateinit var localDataSource: DataSource

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
        settingRepository = SettingRepositoryImpl(localDataSource)
    }

    @Test
    fun `getAllSetting test`() {
        val dummySetting = SettingValue(
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
        every { localDataSource.getAllSetting() } returns dummySetting
        assertThat(settingValue).isNull()
        settingValue = settingRepository.getAllSetting()
        assertThat(settingValue).isEqualTo(expectedSettingValue)
    }

    @Test
    fun `saveAllSetting test`() {
        every { localDataSource.saveAllSetting(any()) } returns Unit
        settingRepository.saveAllSetting(expectedSettingValue)
        verify(exactly = 1) {
            localDataSource.saveAllSetting(any())
            settingRepository.saveAllSetting(any())
        }
    }
}