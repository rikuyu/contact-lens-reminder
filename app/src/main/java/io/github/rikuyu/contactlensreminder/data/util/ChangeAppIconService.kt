package io.github.rikuyu.contactlensreminder.data.util

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager

class ChangeAppIconService(val context: Context) {

    private val packageManager: PackageManager = context.packageManager
    private val enabled = PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    private val disabled = PackageManager.COMPONENT_ENABLED_STATE_DISABLED
    private val dontKillApp = PackageManager.DONT_KILL_APP
    private val pkg = "com.rikuyu.contactlensreminder."
    private val defaultIcon = pkg + "DefaultAlias"
    private val expiredIcon = pkg + "ExpiredAlias"

    private val aliasList: List<String> = listOf(
        "OneAlias",
        "TwoAlias",
        "ThreeAlias",
        "FourAlias",
        "FiveAlias",
        "SixAlias",
        "SevenAlias",
        "EightAlias",
        "NineAlias",
        "TenAlias",
        "ElevenAlias",
        "TwelveAlias",
        "ThirteenAlias",
        "FourteenAlias",
        "FifteenAlias",
        "SixteenAlias",
        "SeventeenAlias",
        "EighteenAlias",
        "NineteenAlias",
        "TwentyAlias",
        "TwentyOneAlias",
        "TwentyTwoAlias",
        "TwentyThreeAlias",
        "TwentyFourAlias",
        "TwentyFiveAlias",
        "TwentySixAlias",
        "TwentySevenAlias",
        "TwentyEightAlias",
        "TwentyNineAlias",
        "ThirtyAlias",
        "ThirtyOneAlias"
    )

    fun changeAppIcon(context: Context, isUsingContactLens: Boolean, lensElapsedDays: Int?) {
        if (isUsingContactLens && lensElapsedDays != null) {
            aliasList.forEachIndexed { index, alias ->
                when {
                    lensElapsedDays - 1 < 0 -> {
                        packageManager.setComponentEnabledSetting(
                            ComponentName(context, expiredIcon),
                            enabled,
                            dontKillApp
                        )
                        packageManager.setComponentEnabledSetting(
                            ComponentName(context, pkg + "OneAlias"), disabled, dontKillApp
                        )
                        packageManager.setComponentEnabledSetting(
                            ComponentName(context, defaultIcon), disabled, dontKillApp
                        )
                    }
                    index == lensElapsedDays - 1 -> {
                        packageManager.setComponentEnabledSetting(
                            ComponentName(context, pkg + alias),
                            enabled,
                            dontKillApp
                        )
                        packageManager.setComponentEnabledSetting(
                            ComponentName(context, defaultIcon), disabled, dontKillApp
                        )
                    }
                    else -> {
                        packageManager.setComponentEnabledSetting(
                            ComponentName(context, pkg + alias), disabled, dontKillApp
                        )
                        packageManager.setComponentEnabledSetting(
                            ComponentName(context, defaultIcon), disabled, dontKillApp
                        )
                    }
                }
            }
        } else {
            // DefaultAlias
            aliasList.forEach { alias ->
                packageManager.setComponentEnabledSetting(
                    ComponentName(context, pkg + alias), disabled, dontKillApp
                )
            }
            packageManager.setComponentEnabledSetting(
                ComponentName(context, defaultIcon), enabled, dontKillApp
            )
        }
    }
}