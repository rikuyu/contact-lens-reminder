package io.github.rikuyu.contactlensreminder.data.local.alarm.notification

import android.app.AlarmManager
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowAlarmManager
import java.text.SimpleDateFormat
import java.util.*

@RunWith(RobolectricTestRunner::class)
class NotificationAlarmServiceTest {

    private lateinit var context: Context
    private lateinit var alarmManager: AlarmManager
    private lateinit var shadowAlarmManager: ShadowAlarmManager
    private lateinit var sharedPreferencesService: SharedPreferencesService
    private lateinit var notificationAlarmService: NotificationAlarmService

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        shadowAlarmManager = shadowOf(alarmManager)
        sharedPreferencesService = SharedPreferencesService(context)
        notificationAlarmService = NotificationAlarmService(context, sharedPreferencesService)
    }

    @Test
    fun `通知を予約できるかどうか`() {
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()

        notificationAlarmService.initAlarm()
        val expectedNotificationTime = getNotificationTime()

        val scheduledAlarm = shadowAlarmManager.nextScheduledAlarm
        assertThat(scheduledAlarm).isNotNull()

        val actualScheduledTime = scheduledAlarm.triggerAtTime

        val before = expectedNotificationTime - 5000L
        val after = expectedNotificationTime + 5000L

        assertThat(actualScheduledTime).isGreaterThan(before)
        assertThat(actualScheduledTime).isLessThan(after)
    }

    @Test
    fun `通知をキャンセルできるかどうか`() {
        notificationAlarmService.initAlarm()
        assertThat(shadowAlarmManager.scheduledAlarms.size).isEqualTo(1)
        notificationAlarmService.cancelAlarm()
        assertThat(shadowAlarmManager.scheduledAlarms.size).isEqualTo(0)
    }

    private fun getNotificationTime(): Long {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("HH/mm/ss", Locale.getDefault())
        val (hour, min, sec) = simpleDateFormat.format(calendar.time).split("/").map(String::toInt)
        calendar.apply {
            timeInMillis = System.currentTimeMillis()
            add(
                Calendar.DAY_OF_MONTH,
                sharedPreferencesService.getContactLensPeriod() - sharedPreferencesService.getNotificationDay()
            )
            add(Calendar.HOUR, sharedPreferencesService.getNotificationTimeHour() - hour)
            add(Calendar.MINUTE, sharedPreferencesService.getNotificationTimeMinute() - min)
            add(Calendar.SECOND, -sec)
        }
        return calendar.timeInMillis
    }
}