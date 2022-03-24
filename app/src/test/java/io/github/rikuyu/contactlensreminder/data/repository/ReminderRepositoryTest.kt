package io.github.rikuyu.contactlensreminder.data.repository

import com.google.common.truth.Truth.assertThat
import io.github.rikuyu.contactlensreminder.domain.local.DataSource
import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue
import io.github.rikuyu.contactlensreminder.domain.repository.ReminderRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ReminderRepositoryTest {

    lateinit var reminderRepository: ReminderRepository

    @MockK
    lateinit var localDataSource: DataSource

    private val expectedReminderValue = ReminderValue(
        lensPeriod = 22,
        lensRemainingDays = 19,
        exchangeDay = "2022/02/11",
        notificationDay = 0,
        notificationTimeHour = 21,
        notificationTimeMinute = 7,
        isUsingContactLens = false,
        isUseNotification = true
    )

    private var reminderValue: ReminderValue? = null

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        reminderRepository = ReminderRepositoryImpl(localDataSource)
    }

    @Test
    fun `getReminderSetting test`() {
        val dummyReminderValue = ReminderValue(
            lensPeriod = 22,
            lensRemainingDays = 19,
            exchangeDay = "2022/02/11",
            notificationDay = 0,
            notificationTimeHour = 21,
            notificationTimeMinute = 7,
            isUsingContactLens = false,
            isUseNotification = true
        )

        every { localDataSource.getReminderSetting() } returns dummyReminderValue

        assertThat(reminderValue).isNull()
        reminderValue = reminderRepository.getReminderSetting()
        assertThat(reminderValue).isEqualTo(expectedReminderValue)

        verify(exactly = 1) {
            localDataSource.getReminderSetting()
            reminderRepository.getReminderSetting()
        }
    }

    @Test
    fun `startReminder test`() {
        every { localDataSource.startReminder() } returns Unit

        reminderRepository.startReminder()

        verify(exactly = 1) {
            localDataSource.startReminder()
            reminderRepository.startReminder()
        }
    }

    @Test
    fun `saveReminderSetting test`() {
        every { localDataSource.saveReminderSetting(any()) } returns Unit

        reminderRepository.saveReminderSetting(expectedReminderValue)

        verify(exactly = 1) {
            localDataSource.saveReminderSetting(any())
            reminderRepository.saveReminderSetting(any())
        }
    }

    @Test
    fun `cancelReminder test`() {
        every { localDataSource.cancelReminder() } returns Unit

        reminderRepository.cancelReminder()

        verify(exactly = 1) {
            localDataSource.cancelReminder()
            reminderRepository.cancelReminder()
        }
    }
}