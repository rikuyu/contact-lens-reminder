package com.example.contactlensreminder.presentation.screens.app_setting.main_screen.components

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.screens.app_setting.AppSettingSection
import com.example.contactlensreminder.presentation.theme.CleanBlue
import com.example.contactlensreminder.presentation.theme.SmoothGray
import com.example.contactlensreminder.presentation.util.Routes
import com.example.contactlensreminder.presentation.util.SimpleDivider
import com.example.contactlensreminder.presentation.util.SimpleSpacer

@Composable
fun AppSettingScreen(
    navController: NavController
) {
    val sectionList = listOf(
        AppSettingSection(stringResource(id = R.string.terms_of_service), Routes.TERMS_OF_SERVICE),
        AppSettingSection(stringResource(id = R.string.help), Routes.HELP),
        AppSettingSection(stringResource(id = R.string.inquiry), Routes.INQUIRY),
        AppSettingSection(stringResource(id = R.string.version, getVersionName(LocalContext.current)), null)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_setting_screen_title))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Routes.TOP) }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                contentColor = Color.White,
                backgroundColor = CleanBlue
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(SmoothGray)
        ) {
            item { SimpleSpacer(height = 20.dp, color = SmoothGray) }
            item { SimpleDivider() }
            items(sectionList) { item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { item.route?.let { navController.navigate(it) } }
                            .padding(all = 16.dp)
                    ) {
                        Text(text = item.title, color = Color.Black)
                    }
                    SimpleDivider()
                }
            }
        }
    }
}

fun getVersionName(context: Context): String {
    val pm = context.packageManager
    var versionName = ""
    try {
        val packageInfo = pm.getPackageInfo(context.packageName, 0)
        versionName = packageInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return versionName
}