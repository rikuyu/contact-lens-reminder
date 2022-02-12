package io.github.rikuyu.contactlensreminder.data.util

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import javax.inject.Inject

class ChangeAppIconService @Inject constructor(val context: Context) {

    private val packageManager: PackageManager = context.packageManager
    private val enabled = PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    private val disabled = PackageManager.COMPONENT_ENABLED_STATE_DISABLED
    private val dontKillApp = PackageManager.DONT_KILL_APP

    val aliasList: List<String> = listOf(
        "OneAlias", "TwoAlias", "ThreeAlias", "FourAlias",
        "FiveAlias", "SixAlias", "SevenAlias", "EightAlias",
        "NineAlias", "TenAlias", "ElevenAlias", "TwelveAlias",
        "ThirteenAlias", "FourteenAlias", "FifteenAlias", "SixteenAlias",
        "SeventeenAlias", "EighteenAlias", "NineteenAlias", "TwentyAlias",
        "TwentyOneAlias", "TwentyTwoAlias", "TwentyThreeAlias", "TwentyFourAlias",
        "TwentyFiveAlias", "TwentySixAlias", "TwentySevenAlias", "TwentyEightAlias",
        "TwentyNineAlias", "ThirtyAlias", "ThirtyOneAlias"
    )

    fun changeAppIcon(isUsingContactLens: Boolean, remainingDay: Int?) {
        val packageName = context.packageName
        val defaultAlias = "$packageName.DefaultAlias"
        val expiredAlias = "$packageName.ExpiredAlias"
        if (isUsingContactLens && remainingDay != null) {
            if (remainingDay < 1) {
                packageManager.setComponentEnabledSetting(
                    ComponentName(context, expiredAlias), enabled, dontKillApp
                )
                packageManager.setComponentEnabledSetting(
                    ComponentName(context, defaultAlias), disabled, dontKillApp
                )
                aliasList.forEach { alias ->
                    packageManager.setComponentEnabledSetting(
                        ComponentName(context, "$packageName.$alias"), disabled, dontKillApp
                    )
                }
                return
            }
            packageManager.setComponentEnabledSetting(
                ComponentName(context, defaultAlias), disabled, dontKillApp
            )
            packageManager.setComponentEnabledSetting(
                ComponentName(context, expiredAlias), disabled, dontKillApp
            )
            aliasList.forEachIndexed { index, alias ->
                if (index == remainingDay - 1) {
                    packageManager.setComponentEnabledSetting(
                        ComponentName(context, "$packageName.$alias"),
                        enabled,
                        dontKillApp
                    )
                } else {
                    packageManager.setComponentEnabledSetting(
                        ComponentName(context, "$packageName.$alias"), disabled, dontKillApp
                    )
                }
            }
        } else {
            // DefaultAlias
            packageManager.setComponentEnabledSetting(
                ComponentName(context, defaultAlias), enabled, dontKillApp
            )
            aliasList.forEach { alias ->
                packageManager.setComponentEnabledSetting(
                    ComponentName(context, "$packageName.$alias"), disabled, dontKillApp
                )
            }
            packageManager.setComponentEnabledSetting(
                ComponentName(context, expiredAlias), disabled, dontKillApp
            )
        }
    }
}