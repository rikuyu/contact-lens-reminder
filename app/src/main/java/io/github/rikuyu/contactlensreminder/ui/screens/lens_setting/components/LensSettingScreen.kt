package io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.components

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.LensSettingEvent
import io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.LensSettingViewModel
import io.github.rikuyu.contactlensreminder.ui.util.*
import io.github.rikuyu.contactlensreminder.ui.util.theme.ThemeColor

@Composable
fun LensSettingScreen(
    isDarkTheme: Boolean,
    themeColor: ThemeColor,
    navController: NavController,
    launcher: ActivityResultLauncher<Intent>,
    setActivityResultLauncher: (() -> Unit) -> Unit,
    viewModel: LensSettingViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val settingValue by viewModel.lensSetting

    var lensPeriod by remember { mutableStateOf(settingValue.lensPeriod) }

    val setLensPeriod: (Int) -> Unit = { index ->
        when (index) {
            0 -> lensPeriod = 14
            1 -> lensPeriod = 31
            2 -> {
            }
        }
    }

    var dialogState by remember { mutableStateOf(false) }

    val notificationManager = NotificationManagerCompat.from(context)

    LaunchedEffect(Unit) {
        if (settingValue.isUseNotification && !notificationManager.areNotificationsEnabled()) {
            dialogState = true
        }
        setActivityResultLauncher {
            if (notificationManager.areNotificationsEnabled()) {
                viewModel.onEvent(LensSettingEvent.IsUseNotification(true))
            } else {
                dialogState = true
                viewModel.onEvent(LensSettingEvent.IsUseNotification(false))
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.lens_setting_screen_title),
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
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
                    .weight(1f)
            ) {
                SimpleDivider()
                SimpleSpacer(height = 20.dp, color = MaterialTheme.colors.surface)
                SimpleDivider()
                LensTypeSection(
                    modifier = Modifier.fillMaxWidth(),
                    lensType = settingValue.lensType
                ) {
                    setLensPeriod(it)
                    viewModel.onEvent(LensSettingEvent.LensType(it))
                    viewModel.onEvent(LensSettingEvent.LensPeriod(lensPeriod))
                }
                AnimatedVisibility(visible = settingValue.lensType != 2) { SimpleDivider() }
                AnimatedVisibility(visible = settingValue.lensType == 2) {
                    OtherTypeLensPeriodSection(
                        isDarkTheme = isDarkTheme,
                        modifier = Modifier.fillMaxWidth(),
                        period = lensPeriod,
                        setLensPeriod = {
                            lensPeriod = it
                            viewModel.onEvent(LensSettingEvent.LensPeriod(it))
                        }
                    )
                }
                NotificationToggleSection(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.notification),
                    isUseNotification = settingValue.isUseNotification,
                    flag = settingValue.isUseNotification
                ) {
                    viewModel.onEvent(LensSettingEvent.IsUseNotification(!settingValue.isUseNotification))
                    if (settingValue.isUseNotification && !notificationManager.areNotificationsEnabled()) {
                        dialogState = true
                    }
                }
                SimpleDivider()
                if (dialogState) {
                    AlertDialog(
                        onDismissRequest = { },
                        shape = RoundedCornerShape(10.dp),
                        title = {
                            Text(text = stringResource(id = R.string.notification_dialog_title))
                        },
                        text = {
                            Text(text = stringResource(id = R.string.notification_dialog_content))
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    dialogState = false
                                    launcher.launch(makeNotificationSettingIntent(context))
                                }
                            ) {
                                Text(
                                    text = stringResource(id = R.string.btn_ok),
                                    color = MaterialTheme.colors.primary
                                )
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    dialogState = false
                                    viewModel.onEvent(LensSettingEvent.IsUseNotification(false))
                                }
                            ) {
                                Text(
                                    text = stringResource(id = R.string.btn_cancel),
                                    color = MaterialTheme.colors.primary
                                )
                            }
                        }
                    )
                }
                AnimatedVisibility(visible = settingValue.isUseNotification) {
                    Column {
                        NotificationDaySection(
                            modifier = Modifier.fillMaxWidth(),
                            lensPeriod = lensPeriod,
                            showToast = { showToast(context, R.string.alert_lens_setting) },
                            notificationType = settingValue.notificationDay,
                            setNotificationType = {
                                viewModel.onEvent(LensSettingEvent.NotificationDay(it))
                            }
                        )
                        NotificationTimeSection(
                            isDarkTheme = isDarkTheme,
                            themeColor = themeColor,
                            modifier = Modifier.fillMaxWidth(),
                            notificationTimeHour = settingValue.notificationTimeHour,
                            setNotificationTimeHour = {
                                viewModel.onEvent(LensSettingEvent.NotificationTimeHour(it))
                            },
                            notificationTimeMinute = settingValue.notificationTimeMinute,
                            setNotificationTimeMinute = {
                                viewModel.onEvent(LensSettingEvent.NotificationTimeMinute(it))
                            }
                        )
                    }
                }
                LensPowerToggleSection(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.notification),
                    flag = settingValue.isShowLensPowerSection
                ) {
                    viewModel.onEvent(LensSettingEvent.IsShowLensPowerSection(!settingValue.isShowLensPowerSection))
                }
                SimpleDivider()
                AnimatedVisibility(visible = settingValue.isShowLensPowerSection) {
                    LensPowerSection(
                        isDarkTheme = isDarkTheme,
                        modifier = Modifier.fillMaxWidth(),
                        leftLensPower = settingValue.leftLensPower.toDouble(),
                        setLeftLensPower = {
                            viewModel.onEvent(LensSettingEvent.LeftPower(it.toString()))
                        },
                        rightLensPower = settingValue.rightLensPower.toDouble(),
                        setRightLensPower = {
                            viewModel.onEvent(LensSettingEvent.RightPower(it.toString()))
                        }
                    )
                }
            }
            SaveSettingButton(modifier = Modifier.fillMaxWidth()) {
                viewModel.onEvent(LensSettingEvent.SaveLensSetting)
                navController.navigate(Routes.TOP) {
                    popUpTo(Routes.TOP) { inclusive = true }
                }
            }
        }
    }
}