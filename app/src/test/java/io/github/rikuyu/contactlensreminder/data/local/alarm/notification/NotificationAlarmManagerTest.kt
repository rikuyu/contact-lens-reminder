package io.github.rikuyu.contactlensreminder.data.local.alarm.notification

import android.app.AlarmManager
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowAlarmManager
import java.text.SimpleDateFormat
import java.util.*

@RunWith(RobolectricTestRunner::class)
class NotificationAlarmManagerTest {

    private lateinit var context: Context
    private lateinit var alarmManager: AlarmManager
    private lateinit var shadowAlarmManager: ShadowAlarmManager
    private lateinit var sharedPreferencesManager: SharedPreferencesManager
    private lateinit var notificationAlarmManager: NotificationAlarmManager

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        shadowAlarmManager = shadowOf(alarmManager)
        sharedPreferencesManager = SharedPreferencesManager(context)
        notificationAlarmManager = NotificationAlarmManager(context, sharedPreferencesManager)
    }

    @Test
    fun `通知を予約できるかどうか`() {
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()

        notificationAlarmManager.initAlarm()
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
        notificationAlarmManager.initAlarm()
        assertThat(shadowAlarmManager.scheduledAlarms.size).isEqualTo(1)
        notificationAlarmManager.cancelAlarm()
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
                sharedPreferencesManager.getContactLensPeriod() - sharedPreferencesManager.getNotificationDay()
            )
            add(Calendar.HOUR, sharedPreferencesManager.getNotificationTimeHour() - hour)
            add(Calendar.MINUTE, sharedPreferencesManager.getNotificationTimeMinute() - min)
            add(Calendar.SECOND, -sec)
        }
        return calendar.timeInMillis
    }
}