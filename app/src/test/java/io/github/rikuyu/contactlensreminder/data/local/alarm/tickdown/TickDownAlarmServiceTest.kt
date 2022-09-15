package io.github.rikuyu.contactlensreminder.data.local.alarm.tickdown

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
class TickDownAlarmServiceTest {

    private lateinit var context: Context
    private lateinit var alarmManager: AlarmManager
    private lateinit var shadowAlarmManager: ShadowAlarmManager
    private lateinit var tickDownAlarmService: TickDownAlarmService
    private lateinit var sharedPreferencesService: SharedPreferencesService

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        shadowAlarmManager = shadowOf(alarmManager)
        sharedPreferencesService = SharedPreferencesService(context)
        tickDownAlarmService = TickDownAlarmService(context)
    }

    @Test
    fun `AppWidget更新タスクを予約できるかどうか`() {
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()

        tickDownAlarmService.initAlarm()
        val dateChangeTime = getDateChangeTime()

        val scheduledAlarm = shadowAlarmManager.nextScheduledAlarm
        assertThat(scheduledAlarm).isNotNull()

        val actualScheduledTime = scheduledAlarm.triggerAtTime

        // initAlarm() 内で取得した Calendar#getTtimeInMillis()
        // と等しい 秒数を取得するのは無理なので
        // before < actualScheduledTime < after と
        // になっていれば指定時刻に通知がスケジュールされたとする。
        val before = dateChangeTime - 5000L
        val after = dateChangeTime + 5000L

        assertThat(actualScheduledTime).isGreaterThan(before)
        assertThat(actualScheduledTime).isLessThan(after)
    }

    @Test
    fun `AppWidget更新タスクをキャンセルできるかどうか`() {
        tickDownAlarmService.initAlarm()
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNotNull()
        tickDownAlarmService.cancelAlarm()
        assertThat(shadowAlarmManager.nextScheduledAlarm).isNull()
    }

    private fun getDateChangeTime(): Long {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("HH/mm/ss", Locale.getDefault())
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