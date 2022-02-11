package io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown

import android.app.AlarmManager
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowAlarmManager

@RunWith(RobolectricTestRunner::class)
class TickDownAlarmManagerTest {

    private lateinit var context: Context
    private lateinit var alarmManager: AlarmManager
    private lateinit var shadowAlarmManager: ShadowAlarmManager
    private lateinit var tickDownAlarmManager: TickDownAlarmManager

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        shadowAlarmManager = Shadows.shadowOf(alarmManager)
        tickDownAlarmManager = TickDownAlarmManager(context)
    }

    @Test
    fun `アイコン変更タスクを予約できるかどうか`() {
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()
        tickDownAlarmManager.initAlarm()
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNotNull()
    }

    @Test
    fun `アイコン変更タスクをキャンセルできるかどうか`() {
        tickDownAlarmManager.initAlarm()
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNotNull()
        tickDownAlarmManager.cancelAlarm()
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()
    }
}