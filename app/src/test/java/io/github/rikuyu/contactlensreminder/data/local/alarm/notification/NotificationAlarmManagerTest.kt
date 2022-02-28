package io.github.rikuyu.contactlensreminder.data.local.alarm.notification

import android.app.AlarmManager
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowAlarmManager

@RunWith(RobolectricTestRunner::class)
class NotificationAlarmManagerTest {

    private lateinit var context: Context
    private lateinit var alarmManager: AlarmManager
    private lateinit var shadowAlarmManager: ShadowAlarmManager
    private lateinit var notificationAlarmManager: NotificationAlarmManager

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        shadowAlarmManager = shadowOf(alarmManager)
        notificationAlarmManager = NotificationAlarmManager(context)
    }

    @Test
    fun `通知を予約できるかどうか`() {
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()
        notificationAlarmManager.initAlarm()
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNotNull()
    }

    @Test
    fun `通知をキャンセルできるかどうか`() {
        notificationAlarmManager.initAlarm()
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNotNull()
        notificationAlarmManager.cancelAlarm()
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()
    }
}