package io.github.rikuyu.contactlensreminder.data.local

import android.app.AlarmManager
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import io.github.rikuyu.contactlensreminder.data.local.alarm.notification.NotificationAlarmService
import io.github.rikuyu.contactlensreminder.data.local.alarm.notification.NotificationService
import io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown.TickDownAlarmService
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesService
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
    private lateinit var tickDownAlarmService: TickDownAlarmService
    private lateinit var notificationAlarmService: NotificationAlarmService
    private lateinit var sharedPreferencesService: SharedPreferencesService
    private lateinit var firebaseLogEventService: FirebaseLogEventService
    private lateinit var notificationService: NotificationService

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
        sharedPreferencesService = mockk()
        localDataSource = LocalDataSource(
            tickDownAlarmService = tickDownAlarmService,
            sharedPreferencesService = sharedPreferencesService,
            notificationAlarmService = notificationAlarmService,
            firebaseLogEventService = firebaseLogEventService,
            notificationService = notificationService,
        )
    }

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        sharedPreferencesService = SharedPreferencesService(context)
        shadowAlarmManager = Shadows.shadowOf(alarmManager)
        firebaseLogEventService = FirebaseLogEventService(sharedPreferencesService)
        tickDownAlarmService = TickDownAlarmService(context)
        notificationAlarmService = NotificationAlarmService(context, sharedPreferencesService)
        notificationService = NotificationService(context)
    }

    @Test
    fun `リマインダーの設定を保存できるか`() {
        initLocalDataSource()

        every { sharedPreferencesService.getContactLensPeriod() } returns 22
        every { sharedPreferencesService.getNotificationDay() } returns 0
        every { sharedPreferencesService.getNotificationTimeHour() } returns 21
        every { sharedPreferencesService.getNotificationTimeMinute() } returns 7
        every { sharedPreferencesService.getContactLensRemainingDays() } returns 19
        every { sharedPreferencesService.getIsUsingContactLens() } returns false
        every { sharedPreferencesService.getIsUseNotification() } returns true
        every { sharedPreferencesService.getLensExchangeDate() } returns "2022/02/11"

        val actual = localDataSource.getReminderSetting()

        verify(exactly = 1) {
            sharedPreferencesService.getContactLensPeriod()
            sharedPreferencesService.getNotificationDay()
            sharedPreferencesService.getNotificationTimeHour()
            sharedPreferencesService.getNotificationTimeMinute()
            sharedPreferencesService.getContactLensRemainingDays()
            sharedPreferencesService.getIsUsingContactLens()
            sharedPreferencesService.getIsUseNotification()
            sharedPreferencesService.getLensExchangeDate()
        }

        confirmVerified(sharedPreferencesService)

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
    fun `コンタクトレンズの設定を保存できるか`() {
        initLocalDataSource()

        every { sharedPreferencesService.getContactLensType() } returns 2
        every { sharedPreferencesService.getContactLensPeriod() } returns 31
        every { sharedPreferencesService.getIsUseNotification() } returns true
        every { sharedPreferencesService.getNotificationDay() } returns 1
        every { sharedPreferencesService.getNotificationTimeHour() } returns 12
        every { sharedPreferencesService.getNotificationTimeMinute() } returns 48
        every { sharedPreferencesService.getIsShowContactLensPowerSection() } returns false
        every { sharedPreferencesService.getLeftContactLensPower() } returns "-4.75"
        every { sharedPreferencesService.getRightContactLensPower() } returns "-5.00"

        val actual = localDataSource.getAllLensSetting()

        verify(exactly = 1) {
            sharedPreferencesService.getContactLensType()
            sharedPreferencesService.getContactLensPeriod()
            sharedPreferencesService.getIsUseNotification()
            sharedPreferencesService.getNotificationDay()
            sharedPreferencesService.getNotificationTimeHour()
            sharedPreferencesService.getNotificationTimeMinute()
            sharedPreferencesService.getIsShowContactLensPowerSection()
            sharedPreferencesService.getRightContactLensPower()
            sharedPreferencesService.getLeftContactLensPower()
        }

        confirmVerified(sharedPreferencesService)

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
    fun `isUseNotification == true のとき通知, AppWidget更新が予約されるかどうか`() {
        initLocalDataSource()

        every { sharedPreferencesService.getIsUseNotification() } returns true
        every { sharedPreferencesService.saveIsExecuteNotification(any()) } returns Unit

        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()
        localDataSource.startReminder()
        assertThat(shadowAlarmManager.scheduledAlarms.size).isEqualTo(2)

        verify(exactly = 1) {
            sharedPreferencesService.getIsUseNotification()
            localDataSource.startReminder()
        }
    }

    @Test
    fun `isUseNotification == false のとき通知, AppWidget更新が予約されるかどうか`() {
        initLocalDataSource()

        every { sharedPreferencesService.getIsUseNotification() } returns false
        every { sharedPreferencesService.saveIsExecuteNotification(any()) } returns Unit

        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()
        localDataSource.startReminder()
        assertThat(shadowAlarmManager.scheduledAlarms.size).isEqualTo(1)

        verify(exactly = 1) {
            sharedPreferencesService.getIsUseNotification()
            localDataSource.startReminder()
        }
    }

    @Test
    fun `isUseNotification == true のとき通知, AppWidget更新がキャンセルされるかどうか`() {
        initLocalDataSource()

        every { sharedPreferencesService.getIsUseNotification() } returns true
        every { sharedPreferencesService.saveIsExecuteNotification(any()) } returns Unit

        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()
        localDataSource.startReminder()
        assertThat(shadowAlarmManager.scheduledAlarms.size).isEqualTo(2)
        localDataSource.resetReminder()
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()

        verifyAll {
            sharedPreferencesService.getIsUseNotification()
            sharedPreferencesService.saveIsExecuteNotification(any())
            localDataSource.startReminder()
            localDataSource.resetReminder()
        }
    }

    @Test
    fun `isUseNotification == false のとき通知, AppWidget更新が変更がキャンセルされるかどうか`() {
        initLocalDataSource()

        every { sharedPreferencesService.getIsUseNotification() } returns false
        every { sharedPreferencesService.saveIsExecuteNotification(any()) } returns Unit

        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()
        localDataSource.startReminder()
        assertThat(shadowAlarmManager.scheduledAlarms.size).isEqualTo(1)
        localDataSource.resetReminder()
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()

        verifyAll {
            sharedPreferencesService.getIsUseNotification()
            localDataSource.startReminder()
            localDataSource.resetReminder()
        }
    }

    @Test
    fun `テーマカラーを取得できるか`() {
        initLocalDataSource()

        every { sharedPreferencesService.getThemeColor() } returns ThemeColor.Blue.name.lowercase()
        assertThat(localDataSource.getThemeColor()).isEqualTo(ThemeColor.Blue.name.lowercase())

        verify(exactly = 1) {
            sharedPreferencesService.getThemeColor()
            localDataSource.getThemeColor()
        }
    }
}