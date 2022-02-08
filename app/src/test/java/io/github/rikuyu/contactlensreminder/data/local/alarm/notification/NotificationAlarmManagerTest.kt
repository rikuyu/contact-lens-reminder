package io.github.rikuyu.contactlensreminder.data.local.alarm.notification

import android.app.AlarmManager
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import io.github.rikuyu.contactlensreminder.data.local.sharedpreferences.SharedPreferencesManager
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
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
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        shadowAlarmManager = shadowOf(alarmManager)
        sharedPreferencesManager = SharedPreferencesManager(context)
        notificationAlarmManager = NotificationAlarmManager(context, sharedPreferencesManager)
    }

    @Test
    fun `will the notifications be set`() {
        assertNull(shadowAlarmManager.nextScheduledAlarm)
        notificationAlarmManager.initAlarm()
        assertNotNull(shadowAlarmManager.nextScheduledAlarm)
    }

    @Test
    fun `will notifications be cancel`() {
        notificationAlarmManager.initAlarm()
        assertNotNull(shadowAlarmManager.nextScheduledAlarm)
        notificationAlarmManager.cancelAlarm()
        assertNull(shadowAlarmManager.nextScheduledAlarm)
    }
}