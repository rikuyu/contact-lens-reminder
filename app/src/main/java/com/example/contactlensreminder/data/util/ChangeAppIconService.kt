package com.example.contactlensreminder.data.util

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager

class ChangeAppIconService(val context: Context) {

    private val packageManager: PackageManager = context.packageManager

    private val enabled = PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    private val disabled = PackageManager.COMPONENT_ENABLED_STATE_DISABLED

    private val DONT_KILL_APP = PackageManager.DONT_KILL_APP

    private val lensPeriodClassList: List<String> = listOf(
        "ZeroAlias",
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

    fun changeAppIcon(context: Context, isUsingContactLens: Boolean, lensPeriod: Int) {
        if (isUsingContactLens) {
            lensPeriodClassList.forEachIndexed { index, alias ->
                val cls = "com.example.contactlensreminder.$alias"
                when {
                    index == lensPeriod -> {
                        packageManager.setComponentEnabledSetting(
                            ComponentName(context, cls), enabled, DONT_KILL_APP
                        )
                    }
                    lensPeriod < 0 -> {
                        packageManager.setComponentEnabledSetting(
                            ComponentName(context, lensPeriodClassList.last()),
                            enabled,
                            DONT_KILL_APP
                        )
                    }
                    else -> {
                        packageManager.setComponentEnabledSetting(
                            ComponentName(context, cls), disabled, DONT_KILL_APP
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
                        ComponentName(context, cls), enabled, DONT_KILL_APP
                    )
                } else {
                    packageManager.setComponentEnabledSetting(
                        ComponentName(context, cls), disabled, DONT_KILL_APP
                    )
                }
            }
        }
    }
}