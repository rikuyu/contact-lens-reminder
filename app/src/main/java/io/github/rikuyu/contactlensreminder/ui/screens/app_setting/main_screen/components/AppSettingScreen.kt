package io.github.rikuyu.contactlensreminder.ui.screens.app_setting.main_screen.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
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
import io.github.rikuyu.contactlensreminder.BuildConfig
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.ui.screens.app_setting.AppSettingEvent
import io.github.rikuyu.contactlensreminder.ui.screens.app_setting.AppSettingItem
import io.github.rikuyu.contactlensreminder.ui.screens.app_setting.AppSettingViewModel
import io.github.rikuyu.contactlensreminder.ui.screens.app_setting.color_theme.ColorPickerDialog
import io.github.rikuyu.contactlensreminder.ui.theme.ThemeColor
import io.github.rikuyu.contactlensreminder.ui.util.Routes
import io.github.rikuyu.contactlensreminder.ui.util.SimpleDivider
import io.github.rikuyu.contactlensreminder.ui.util.SimpleSpacer
import io.github.rikuyu.contactlensreminder.ui.util.makeNotificationSettingIntent

@Composable
fun AppSettingScreen(
    themeColor: ThemeColor,
    changeThemeColor: (ThemeColor) -> Unit,
    navController: NavController,
    viewModel: AppSettingViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val version = BuildConfig.VERSION_NAME
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    val scrollState = rememberScrollState()

    var dialogState by remember { mutableStateOf(false) }

    val sectionList = listOf(
        AppSettingItem(
            1,
            stringResource(id = R.string.terms_of_service),
            R.drawable.ic_terms_of_service,
            Routes.TERMS_OF_SERVICE
        ),
        AppSettingItem(
            2,
            stringResource(id = R.string.help),
            R.drawable.ic_help,
            Routes.HELP
        ),
        AppSettingItem(
            3,
            stringResource(id = R.string.notification_setting),
            R.drawable.ic_notify,
            "notification_setting"
        ),
        AppSettingItem(
            4,
            stringResource(id = R.string.inquiry),
            R.drawable.ic_inquiry,
            Routes.INQUIRY
        ),
        AppSettingItem(
            5,
            stringResource(id = R.string.color_theme),
            R.drawable.ic_palette,
            "change_theme_color"
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
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .verticalScroll(scrollState)
            ) {
                SimpleSpacer(height = 20.dp)
                SimpleDivider()
                sectionList.forEach {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.background)
                                .clickable {
                                    viewModel.onEvent(AppSettingEvent.LogEvent(it.route))
                                    when (it.id) {
                                        3 -> context.startActivity(makeNotificationSettingIntent(context))
                                        5 -> dialogState = true
                                        else -> navController.navigate(it.route)
                                    }
                                }
                                .padding(all = 16.dp)
                        ) {
                            Text(text = it.title, color = MaterialTheme.colors.onSurface)
                            Icon(
                                modifier = Modifier.align(Alignment.CenterEnd),
                                painter = painterResource(id = it.icon),
                                tint = MaterialTheme.colors.primary,
                                contentDescription = null
                            )
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
                        indication = rememberRipple(
                            color = MaterialTheme.colors.primary,
                            bounded = false,
                            radius = 50.dp
                        )
                    ) {
                        clipboardManager.setText(AnnotatedString(version))
                        Toast
                            .makeText(context, "$version copied", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .padding(vertical = 8.dp, horizontal = 10.dp)
            )
            ColorPickerDialog(
                stateColor = themeColor,
                dialogState = dialogState,
                changeDialogState = { dialogState = it },
            ) {
                changeThemeColor(it)
                viewModel.onEvent(AppSettingEvent.SaveThemeColor(it))
            }
        }
    }
}