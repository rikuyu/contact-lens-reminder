package io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.notification_setting

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.components.SaveSettingButton
import io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.components.SetNotificationDaySection
import io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.components.SetNotificationTimeSection
import io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.components.ToggleButtonSection
import io.github.rikuyu.contactlensreminder.ui.theme.CleanBlue
import io.github.rikuyu.contactlensreminder.ui.theme.SmoothGray
import io.github.rikuyu.contactlensreminder.ui.util.Routes
import io.github.rikuyu.contactlensreminder.ui.util.SimpleDivider
import io.github.rikuyu.contactlensreminder.ui.util.SimpleSpacer

@Composable
fun NotificationSettingScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.notification_setting_screen_title),
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = CleanBlue
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    SimpleDivider()
                    SimpleSpacer(height = 20.dp, color = SmoothGray)
                    SimpleDivider()
                    ToggleButtonSection(
                        context = context,
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.notification),
                        isShowIcon = true,
                        flag = true
                    ) {
                        // Todo
                    }
                    SimpleDivider()
                    AnimatedVisibility(visible = true) {
                        Column {
                            SetNotificationDaySection(
                                modifier = Modifier.fillMaxWidth(),
                                notificationType = 11111
                            ) {
                                // Todo
                            }
                            SetNotificationTimeSection(
                                modifier = Modifier.fillMaxWidth(),
                                notificationTimeHour = 1111,
                                setNotificationTimeHour = {
                                    // Todo
                                },
                                notificationTimeMinute = 1111,
                                setNotificationTimeMinute = {
                                    // Todo
                                }
                            )
                        }
                    }
                    SimpleSpacer(height = 4.dp)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 12.dp),
                            fontSize = 24.sp,
                            text = stringResource(id = R.string.asterisk),
                            color = Color.Gray
                        )
                        Text(
                            fontSize = 14.sp,
                            text = stringResource(id = R.string.notification_setting_caution),
                            color = Color.Gray
                        )
                    }
                }
                SaveSettingButton(modifier = Modifier.fillMaxWidth()) {
                    navController.navigate(Routes.TOP)
                }
            }
        }
    )
}