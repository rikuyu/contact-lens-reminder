package com.example.contactlensreminder.presentation.ui.screens.lens_setting

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.MainViewModel
import com.example.contactlensreminder.presentation.ui.theme.CleanBlue
import com.example.contactlensreminder.presentation.ui.theme.SmoothGray
import com.example.contactlensreminder.presentation.util.Routes
import com.example.contactlensreminder.presentation.util.SimpleDivider

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LensSettingScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {

    val decideIsUseNotification = remember {
        mutableStateOf(true)
    }

    val decideLensPeriodState = remember {
        mutableStateOf(false)
    }

    var periodType by remember {
        mutableStateOf(0)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.lens_setting_screen_title))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Routes.TOP) }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = CleanBlue
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SmoothGray)
            ) {
                SimpleDivider()
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .background(SmoothGray)
                )
                SimpleDivider()
                SetLensTypeSection(
                    modifier = Modifier.fillMaxWidth(),
                    periodType = periodType
                ) {
                    periodType = it
                    decideLensPeriodState.value = periodType == 2
                }
                SimpleDivider()
                AnimatedVisibility(visible = decideLensPeriodState.value) {
                    SetChangeLensDaySection(modifier = Modifier.fillMaxWidth())
                }
                SetUseNotification(
                    modifier = Modifier.fillMaxWidth(),
                    decideIsUseNotification = decideIsUseNotification.value
                ) {
                    decideIsUseNotification.value = !decideIsUseNotification.value
                }
                SimpleDivider()
                AnimatedVisibility(visible = decideIsUseNotification.value) {
                    SetNotificationDaySection(modifier = Modifier.fillMaxWidth())
                }
                SetLensPowerSection(modifier = Modifier.fillMaxWidth())
                SimpleDivider()
                SetSettingButton(modifier = Modifier.fillMaxWidth())
            }
        }
    )
}