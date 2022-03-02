package io.github.rikuyu.contactlensreminder.ui.screens.app_setting.main_screen.components

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.ui.screens.app_setting.AppSettingEvent
import io.github.rikuyu.contactlensreminder.ui.screens.app_setting.AppSettingSection
import io.github.rikuyu.contactlensreminder.ui.screens.app_setting.AppSettingViewModel
import io.github.rikuyu.contactlensreminder.ui.theme.CleanBlue
import io.github.rikuyu.contactlensreminder.ui.theme.SkyBlue
import io.github.rikuyu.contactlensreminder.ui.theme.SmoothGray
import io.github.rikuyu.contactlensreminder.ui.util.Routes
import io.github.rikuyu.contactlensreminder.ui.util.SimpleDivider
import io.github.rikuyu.contactlensreminder.ui.util.SimpleSpacer
import io.github.rikuyu.contactlensreminder.ui.util.makeNotificationSettingIntent

@Composable
fun AppSettingScreen(
    navController: NavController,
    viewModel: AppSettingViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val version = getVersionName(context, viewModel)
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    val sectionList = listOf(
        AppSettingSection(
            1,
            stringResource(id = R.string.terms_of_service),
            R.drawable.ic_terms_of_service,
            Routes.TERMS_OF_SERVICE
        ),
        AppSettingSection(
            2,
            stringResource(id = R.string.help),
            R.drawable.ic_help,
            Routes.HELP
        ),
        AppSettingSection(
            3,
            stringResource(id = R.string.notification_setting),
            R.drawable.ic_notify,
            null
        ),
        AppSettingSection(
            4,
            stringResource(id = R.string.inquiry),
            R.drawable.ic_inquiry,
            Routes.INQUIRY
        ),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_setting_screen_title))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                contentColor = Color.White,
                backgroundColor = CleanBlue
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SmoothGray)
        ) {
            LazyColumn(modifier = Modifier.align(Alignment.TopCenter)) {
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
                                .clickable {
                                    if (item.route != null) {
                                        navController.navigate(item.route)
                                        viewModel.onEvent(AppSettingEvent.LogEvent(item.route))
                                    } else if (item.id == 3) {
                                        context.startActivity(makeNotificationSettingIntent(context))
                                        viewModel.onEvent(AppSettingEvent.LogEvent("notification_setting"))
                                    }
                                }
                                .padding(all = 16.dp)
                        ) {
                            Text(text = item.title, color = Color.Black)
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    tint = SkyBlue,
                                    contentDescription = null
                                )
                            }
                        }
                        SimpleDivider()
                    }
                }
            }
            Text(
                text = stringResource(id = R.string.version, version),
                color = Color.Gray,
                fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(color = CleanBlue, bounded = false, radius = 50.dp)
                    ) {
                        clipboardManager.setText(AnnotatedString(version))
                        Toast
                            .makeText(context, "$version copied", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .padding(vertical = 8.dp, horizontal = 10.dp)
            )
        }
    }
}

fun getVersionName(context: Context, viewModel: AppSettingViewModel): String {
    val pm = context.packageManager
    var versionName = ""
    try {
        val packageInfo = pm.getPackageInfo(context.packageName, 0)
        versionName = packageInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        viewModel.onEvent(AppSettingEvent.LogEvent("$e"))
    }
    return versionName
}