package com.example.contactlensreminder.data.util

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager

class ChangeAppIconService(val context: Context) {

    private val packageManager: PackageManager = context.packageManager

    private val enabled = PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    private val disabled = PackageManager.COMPONENT_ENABLED_STATE_DISABLED

    private val dontKillApp = PackageManager.DONT_KILL_APP

    private val lensPeriodClassList: List<String> = listOf(
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
        "ThirtyOneAlias",
        "DefaultAlias",
        "ExpiredAlias"
    )

    fun changeAppIcon(context: Context, isUsingContactLens: Boolean, lensPeriod: Int?) {
        if (isUsingContactLens && lensPeriod != null) {
            lensPeriodClassList.forEachIndexed { index, alias ->
                val cls = "com.example.contactlensreminder.$alias"
                when {
                    index == lensPeriod -> {
                        packageManager.setComponentEnabledSetting(
                            ComponentName(context, cls), enabled, dontKillApp
                        )
                    }
                    lensPeriod < 1 -> {
                        packageManager.setComponentEnabledSetting(
                            ComponentName(context, lensPeriodClassList.last()),
                            enabled,
                            dontKillApp
                        )
                    }
                    else -> {
                        packageManager.setComponentEnabledSetting(
                            ComponentName(context, cls), disabled, dontKillApp
                        )
                    }
                }
            }
        } else {
            lensPeriodClassList.forEachIndexed { index, alias ->
                val cls = "com.example.contactlensreminder.$alias"
                // DefaultAlias
                if (index == lensPeriodClassList.size - 2) {
                    packageManager.setComponentEnabledSetting(
                        ComponentName(context, cls), enabled, dontKillApp
                    )
                } else {
                    packageManager.setComponentEnabledSetting(
                        ComponentName(context, cls), disabled, dontKillApp
                    )
                }
            }
        }
    }
}