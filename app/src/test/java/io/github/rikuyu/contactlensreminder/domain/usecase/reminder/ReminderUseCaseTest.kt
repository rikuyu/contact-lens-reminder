package io.github.rikuyu.contactlensreminder.domain.usecase.reminder

import com.google.common.truth.Truth.assertThat
import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue
import io.github.rikuyu.contactlensreminder.domain.repository.ReminderRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ReminderUseCaseTest {

    lateinit var saveReminderSetting: SaveReminderSetting
    lateinit var startReminder: StartReminder
    lateinit var getReminderSetting: GetReminderSetting
    lateinit var cancelReminder: CancelReminder
    lateinit var reminderUseCase: ReminderUseCase
    lateinit var getIsShowOnBoarding: GetIsShowOnBoarding
    lateinit var getIsDarkTheme: GetIsDarkTheme
    lateinit var switchIsDarkTheme: SwitchIsDarkTheme

    @MockK
    lateinit var repository: ReminderRepository

    private var reminderValue: ReminderValue? = null

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

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        saveReminderSetting = SaveReminderSetting(repository)
        startReminder = StartReminder(repository)
        getReminderSetting = GetReminderSetting(repository)
        cancelReminder = CancelReminder(repository)
        getIsShowOnBoarding = GetIsShowOnBoarding(repository)
        getIsDarkTheme = GetIsDarkTheme(repository)
        switchIsDarkTheme = SwitchIsDarkTheme(repository)
        reminderUseCase = ReminderUseCase(
            saveReminderSetting,
            startReminder,
            getReminderSetting,
            cancelReminder,
            getIsShowOnBoarding,
            getIsDarkTheme,
            switchIsDarkTheme
        )
    }

    @Test
    fun `GetReminderSetting Test`() {
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
        every { repository.getReminderSetting() } returns dummyReminderValue
        assertThat(reminderValue).isNull()
        reminderValue = reminderUseCase.getReminderSetting.invoke()
        assertThat(reminderValue).isEqualTo(expectedReminderValue)
    }

    @Test
    fun `StartReminder Test`() {
        every { repository.startReminder() } returns Unit
        reminderUseCase.startReminder.invoke()
        verify(exactly = 1) {
            reminderUseCase.startReminder.invoke()
            repository.startReminder()
        }
    }

    @Test
    fun `CancelReminder Test`() {
        every { repository.cancelReminder() } returns Unit
        reminderUseCase.cancelReminder.invoke()
        verify(exactly = 1) {
            reminderUseCase.cancelReminder.invoke()
            repository.cancelReminder()
        }
    }

    @Test
    fun `SaveReminderSetting Test`() {
        every { repository.saveReminderSetting(any()) } returns Unit
        reminderUseCase.saveReminderSetting(expectedReminderValue)
        verify(exactly = 1) {
            reminderUseCase.saveReminderSetting.invoke(any())
            repository.saveReminderSetting(any())
        }
    }
}