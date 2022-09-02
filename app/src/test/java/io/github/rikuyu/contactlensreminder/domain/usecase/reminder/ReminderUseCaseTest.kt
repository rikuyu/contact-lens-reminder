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

    private lateinit var saveReminderSetting: SaveReminderSetting
    private lateinit var startReminder: StartReminder
    private lateinit var getReminderSetting: GetReminderSetting
    private lateinit var resetReminder: ResetReminder
    private lateinit var reminderUseCase: ReminderUseCase
    private lateinit var getIsShowOnBoarding: GetIsShowOnBoarding
    private lateinit var getIsDarkTheme: GetIsDarkTheme
    private lateinit var switchIsDarkTheme: SwitchIsDarkTheme
    private lateinit var createChannel: CreateChannel

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
        resetReminder = ResetReminder(repository)
        getIsShowOnBoarding = GetIsShowOnBoarding(repository)
        getIsDarkTheme = GetIsDarkTheme(repository)
        switchIsDarkTheme = SwitchIsDarkTheme(repository)
        createChannel = CreateChannel(repository)
        reminderUseCase = ReminderUseCase(
            saveReminderSetting,
            startReminder,
            getReminderSetting,
            resetReminder,
            getIsShowOnBoarding,
            getIsDarkTheme,
            switchIsDarkTheme,
            createChannel
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
    fun `ResetReminder Test`() {
        every { repository.resetReminder() } returns Unit
        reminderUseCase.resetReminder.invoke()
        verify(exactly = 1) {
            reminderUseCase.resetReminder.invoke()
            repository.resetReminder()
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

    @Test
    fun `GetIsDarkTheme Test`() {
        every { repository.getIsDarkTheme() } returns false
        val darkTheme = reminderUseCase.getIsDarkTheme.invoke()

        assertThat(darkTheme).isFalse()

        verify(exactly = 1) {
            reminderUseCase.getIsDarkTheme.invoke()
            repository.getIsDarkTheme()
        }
    }

    @Test
    fun `SwitchIsDarkTheme Test`() {
        every { repository.switchIsDarkTheme() } returns Unit
        reminderUseCase.switchIsDarkTheme()
        verify(exactly = 1) {
            reminderUseCase.switchIsDarkTheme.invoke()
            repository.switchIsDarkTheme()
        }
    }
}