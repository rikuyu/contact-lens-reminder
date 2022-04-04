package io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown

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
class TickDownAlarmManagerTest {

    private lateinit var context: Context
    private lateinit var alarmManager: AlarmManager
    private lateinit var shadowAlarmManager: ShadowAlarmManager
    private lateinit var tickDownAlarmManager: TickDownAlarmManager
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        shadowAlarmManager = shadowOf(alarmManager)
        sharedPreferencesManager = SharedPreferencesManager(context)
        tickDownAlarmManager = TickDownAlarmManager(context)
    }

    @Test
    fun `AppWidget更新タスクを予約できるかどうか`() {
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()

        tickDownAlarmManager.initAlarm()

        val scheduledAlarm = shadowAlarmManager.nextScheduledAlarm
        assertThat(scheduledAlarm).isNotNull()

        val actualScheduledTime = scheduledAlarm.triggerAtTime
        val dateChangeTime = getDateChangeTime()

        // initAlarm() 内で取得した Calendar#getTtimeInMillis()
        // と等しい 秒数を取得するのは無理なので
        // before < actualScheduledTime < after と
        // になっていれば指定時刻に通知がスケジュールされたとする。
        val before = dateChangeTime - 100L
        val after = dateChangeTime + 100L

        assertThat(actualScheduledTime).isGreaterThan(before)
        assertThat(actualScheduledTime).isLessThan(after)
    }

    @Test
    fun `AppWidget更新タスクをキャンセルできるかどうか`() {
        tickDownAlarmManager.initAlarm()
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNotNull()
        tickDownAlarmManager.cancelAlarm()
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()
    }

    private fun getDateChangeTime(): Long {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("HH/mm/ss", Locale.ENGLISH)
        val (hour, min, sec) = simpleDateFormat.format(calendar.time).split("/").map(String::toInt)
        calendar.apply {
            timeInMillis = System.currentTimeMillis()
            add(Calendar.HOUR, 24 - hour)
            add(Calendar.MINUTE, -min)
            add(Calendar.SECOND, -sec)
        }
        return calendar.timeInMillis
    }
}