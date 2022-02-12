package io.github.rikuyu.contactlensreminder.data.util

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager.*
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ChangeAppIconServiceTest {

    private lateinit var context: Context
    private lateinit var changeAppIconService: ChangeAppIconService

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
        changeAppIconService = ChangeAppIconService(context)
    }

    @Test
    fun `コンタクトレンズを使用し始めた時、アプリアイコンが変わるか`() {
        val packageName = context.packageName

        // デフォルトアイコン以外が未使用か
        changeAppIconService.aliasList.forEach { alias ->
            val componentName = ComponentName(context, "$packageName.$alias")
            val enabledFlag = context.packageManager.getComponentEnabledSetting(componentName)
            assertThat(enabledFlag).isEqualTo(COMPONENT_ENABLED_STATE_DEFAULT)
        }

        /***  前提  ***/

        // 残り1日アイコンに変更
        changeAppIconService.changeAppIcon(true, 1)

        val oneAlias = "$packageName.${changeAppIconService.aliasList[0]}"
        val oneEnabledFlag = context.packageManager
            .getComponentEnabledSetting(ComponentName(context, oneAlias))

        // 残り1日アイコンか
        assertThat(oneEnabledFlag).isEqualTo(COMPONENT_ENABLED_STATE_ENABLED)

        // 1日アイコン以外が未使用か
        changeAppIconService.aliasList.forEachIndexed { i, alias ->
            if (i != 0) {
                val componentName = ComponentName(context, "$packageName.$alias")
                val enabledFlag = context.packageManager.getComponentEnabledSetting(componentName)
                assertThat(enabledFlag).isEqualTo(COMPONENT_ENABLED_STATE_DISABLED)
            }
        }
    }

    @Test
    fun `コンタクトレンズ未使用の時アプリアイコンがデフォルトに戻るか`() {
        val packageName = context.packageName

        // 残り1日アイコンに変更
        changeAppIconService.changeAppIcon(true, 1)

        val oneAlias = "$packageName.${changeAppIconService.aliasList[0]}"
        val oneEnabledFlag = context.packageManager
            .getComponentEnabledSetting(ComponentName(context, oneAlias))

        // 残り1日アイコンか
        assertThat(oneEnabledFlag).isEqualTo(COMPONENT_ENABLED_STATE_ENABLED)

        /***  前提  ***/

        changeAppIconService.changeAppIcon(false, null)

        // デフォルトアイコン以外が未使用か
        changeAppIconService.aliasList.forEach { alias ->
            val componentName = ComponentName(context, "$packageName.$alias")
            val enabledFlag = context.packageManager.getComponentEnabledSetting(componentName)
            assertThat(enabledFlag).isEqualTo(COMPONENT_ENABLED_STATE_DISABLED)
        }
    }

    @Test
    fun `コンタクトレンズ使用期限が過ぎた後、アプリアイコンが変わるか`() {
        val packageName = context.packageName

        // 残り1日アイコンに変更
        changeAppIconService.changeAppIcon(true, 1)

        val oneAlias = "$packageName.${changeAppIconService.aliasList[0]}"
        val oneEnabledFlag = context.packageManager
            .getComponentEnabledSetting(ComponentName(context, oneAlias))

        // 残り1日アイコンか
        assertThat(oneEnabledFlag).isEqualTo(COMPONENT_ENABLED_STATE_ENABLED)

        /***  前提  ***/

        changeAppIconService.changeAppIcon(false, null)

        // デフォルトアイコン以外が未使用か
        changeAppIconService.aliasList.forEach { alias ->
            val componentName = ComponentName(context, "$packageName.$alias")
            val enabledFlag = context.packageManager.getComponentEnabledSetting(componentName)
            assertThat(enabledFlag).isEqualTo(COMPONENT_ENABLED_STATE_DISABLED)
        }
    }
}