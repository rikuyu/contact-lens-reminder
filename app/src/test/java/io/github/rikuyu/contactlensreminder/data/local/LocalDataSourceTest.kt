package io.github.rikuyu.contactlensreminder.data.local

import android.app.AlarmManager
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import io.github.rikuyu.contactlensreminder.data.local.alarm.notification.NotificationAlarmManager
import io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown.TickDownAlarmManager
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import io.github.rikuyu.contactlensreminder.data.util.FirebaseLogEventService
import io.github.rikuyu.contactlensreminder.domain.local.DataSource
import io.github.rikuyu.contactlensreminder.domain.model.LensSettingValue
import io.github.rikuyu.contactlensreminder.domain.model.ReminderValue
import io.github.rikuyu.contactlensreminder.ui.util.theme.ThemeColor
import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowAlarmManager

@RunWith(RobolectricTestRunner::class)
class LocalDataSourceTest {

    private lateinit var context: Context
    private lateinit var alarmManager: AlarmManager
    private lateinit var shadowAlarmManager: ShadowAlarmManager
    private lateinit var tickDownAlarmManager: TickDownAlarmManager
    private lateinit var notificationAlarmManager: NotificationAlarmManager
    private lateinit var sharedPreferencesManager: SharedPreferencesManager
    private lateinit var firebaseLogEventService: FirebaseLogEventService

    private lateinit var localDataSource: DataSource

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

    private fun initLocalDataSource() {
        sharedPreferencesManager = mockk()
        localDataSource = LocalDataSource(
            tickDownAlarmManager = tickDownAlarmManager,
            sharedPreferencesManager = sharedPreferencesManager,
            notificationAlarmManager = notificationAlarmManager,
            firebaseLogEventService = firebaseLogEventService
        )
    }

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        sharedPreferencesManager = SharedPreferencesManager(context)
        shadowAlarmManager = Shadows.shadowOf(alarmManager)
        firebaseLogEventService = FirebaseLogEventService(sharedPreferencesManager)
        tickDownAlarmManager = TickDownAlarmManager(context)
        notificationAlarmManager = NotificationAlarmManager(context, sharedPreferencesManager)
    }

    @Test
    fun `????????????????????????????????????????????????`() {
        initLocalDataSource()

        every { sharedPreferencesManager.getContactLensPeriod() } returns 22
        every { sharedPreferencesManager.getNotificationDay() } returns 0
        every { sharedPreferencesManager.getNotificationTimeHour() } returns 21
        every { sharedPreferencesManager.getNotificationTimeMinute() } returns 7
        every { sharedPreferencesManager.getContactLensRemainingDays() } returns 19
        every { sharedPreferencesManager.getIsUsingContactLens() } returns false
        every { sharedPreferencesManager.getIsUseNotification() } returns true
        every { sharedPreferencesManager.getLensExchangeDate() } returns "2022/02/11"

        val actual = localDataSource.getReminderSetting()

        verify(exactly = 1) {
            sharedPreferencesManager.getContactLensPeriod()
            sharedPreferencesManager.getNotificationDay()
            sharedPreferencesManager.getNotificationTimeHour()
            sharedPreferencesManager.getNotificationTimeMinute()
            sharedPreferencesManager.getContactLensRemainingDays()
            sharedPreferencesManager.getIsUsingContactLens()
            sharedPreferencesManager.getIsUseNotification()
            sharedPreferencesManager.getLensExchangeDate()
        }

        confirmVerified(sharedPreferencesManager)

        assertThat(actual.lensPeriod).isEqualTo(expectedReminderValue.lensPeriod)
        assertThat(actual.notificationDay).isEqualTo(expectedReminderValue.notificationDay)
        assertThat(actual.notificationTimeHour).isEqualTo(expectedReminderValue.notificationTimeHour)
        assertThat(actual.notificationTimeMinute).isEqualTo(expectedReminderValue.notificationTimeMinute)
        assertThat(actual.lensRemainingDays).isEqualTo(expectedReminderValue.lensRemainingDays)
        assertThat(actual.isUsingContactLens).isEqualTo(expectedReminderValue.isUsingContactLens)
        assertThat(actual.isUseNotification).isEqualTo(expectedReminderValue.isUseNotification)
        assertThat(actual.exchangeDay).isEqualTo(expectedReminderValue.exchangeDay)
    }

    @Test
    fun `??????????????????????????????????????????????????????`() {
        initLocalDataSource()

        every { sharedPreferencesManager.getContactLensType() } returns 2
        every { sharedPreferencesManager.getContactLensPeriod() } returns 31
        every { sharedPreferencesManager.getIsUseNotification() } returns true
        every { sharedPreferencesManager.getNotificationDay() } returns 1
        every { sharedPreferencesManager.getNotificationTimeHour() } returns 12
        every { sharedPreferencesManager.getNotificationTimeMinute() } returns 48
        every { sharedPreferencesManager.getIsShowContactLensPowerSection() } returns false
        every { sharedPreferencesManager.getLeftContactLensPower() } returns "-4.75"
        every { sharedPreferencesManager.getRightContactLensPower() } returns "-5.00"

        val actual = localDataSource.getAllLensSetting()

        verify(exactly = 1) {
            sharedPreferencesManager.getContactLensType()
            sharedPreferencesManager.getContactLensPeriod()
            sharedPreferencesManager.getIsUseNotification()
            sharedPreferencesManager.getNotificationDay()
            sharedPreferencesManager.getNotificationTimeHour()
            sharedPreferencesManager.getNotificationTimeMinute()
            sharedPreferencesManager.getIsShowContactLensPowerSection()
            sharedPreferencesManager.getRightContactLensPower()
            sharedPreferencesManager.getLeftContactLensPower()
        }

        confirmVerified(sharedPreferencesManager)

        assertThat(actual.lensType).isEqualTo(expectedSettingValue.lensType)
        assertThat(actual.lensPeriod).isEqualTo(expectedSettingValue.lensPeriod)
        assertThat(actual.isUseNotification).isEqualTo(expectedSettingValue.isUseNotification)
        assertThat(actual.notificationDay).isEqualTo(expectedSettingValue.notificationDay)
        assertThat(actual.notificationTimeHour).isEqualTo(expectedSettingValue.notificationTimeHour)
        assertThat(actual.notificationTimeMinute).isEqualTo(expectedSettingValue.notificationTimeMinute)
        assertThat(actual.isShowLensPowerSection).isEqualTo(expectedSettingValue.isShowLensPowerSection)
        assertThat(actual.rightLensPower).isEqualTo(expectedSettingValue.rightLensPower)
        assertThat(actual.leftLensPower).isEqualTo(expectedSettingValue.leftLensPower)
    }

    @Test
    fun `isUseNotification == true ???????????????, AppWidget????????????????????????????????????`() {
        initLocalDataSource()

        every { sharedPreferencesManager.getIsUseNotification() } returns true
        every { sharedPreferencesManager.saveIsExecuteNotification(any()) } returns Unit

        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()
        localDataSource.startReminder()
        assertThat(shadowAlarmManager.scheduledAlarms.size).isEqualTo(2)

        verify(exactly = 1) {
            sharedPreferencesManager.getIsUseNotification()
            localDataSource.startReminder()
        }
    }

    @Test
    fun `isUseNotification == false ???????????????, AppWidget????????????????????????????????????`() {
        initLocalDataSource()

        every { sharedPreferencesManager.getIsUseNotification() } returns false
        every { sharedPreferencesManager.saveIsExecuteNotification(any()) } returns Unit

        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()
        localDataSource.startReminder()
        assertThat(shadowAlarmManager.scheduledAlarms.size).isEqualTo(1)

        verify(exactly = 1) {
            sharedPreferencesManager.getIsUseNotification()
            localDataSource.startReminder()
        }
    }

    @Test
    fun `isUseNotification == true ???????????????, AppWidget?????????????????????????????????????????????`() {
        initLocalDataSource()

        every { sharedPreferencesManager.getIsUseNotification() } returns true
        every { sharedPreferencesManager.saveIsExecuteNotification(any()) } returns Unit

        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()
        localDataSource.startReminder()
        assertThat(shadowAlarmManager.scheduledAlarms.size).isEqualTo(2)
        localDataSource.resetReminder()
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()

        verifyAll {
            sharedPreferencesManager.getIsUseNotification()
            sharedPreferencesManager.saveIsExecuteNotification(any())
            localDataSource.startReminder()
            localDataSource.resetReminder()
        }
    }

    @Test
    fun `isUseNotification == false ???????????????, AppWidget??????????????????????????????????????????????????????`() {
        initLocalDataSource()

        every { sharedPreferencesManager.getIsUseNotification() } returns false
        every { sharedPreferencesManager.saveIsExecuteNotification(any()) } returns Unit

        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()
        localDataSource.startReminder()
        assertThat(shadowAlarmManager.scheduledAlarms.size).isEqualTo(1)
        localDataSource.resetReminder()
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()

        verifyAll {
            sharedPreferencesManager.getIsUseNotification()
            localDataSource.startReminder()
            localDataSource.resetReminder()
        }
    }

    @Test
    fun `???????????????????????????????????????`() {
        initLocalDataSource()

        every { sharedPreferencesManager.getThemeColor() } returns ThemeColor.Blue.name.lowercase()
        assertThat(localDataSource.getThemeColor()).isEqualTo(ThemeColor.Blue.name.lowercase())

        verify(exactly = 1) {
            sharedPreferencesManager.getThemeColor()
            localDataSource.getThemeColor()
        }
    }
}