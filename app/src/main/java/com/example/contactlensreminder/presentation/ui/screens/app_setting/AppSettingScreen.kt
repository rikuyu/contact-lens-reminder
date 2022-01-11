package com.example.contactlensreminder.presentation.ui.screens.app_setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.ui.theme.CleanBlue
import com.example.contactlensreminder.presentation.ui.theme.SmoothGray
import com.example.contactlensreminder.presentation.util.Routes
import com.example.contactlensreminder.presentation.util.SimpleDivider
import com.example.contactlensreminder.presentation.util.SimpleSpacer

@Composable
fun AppSettingScreen(
    navController: NavController
) {

    val list = listOf(
        stringResource(id = R.string.privacy_policy),
        stringResource(id = R.string.help),
        stringResource(
            id = R.string.terms_of_service
        ),
        stringResource(id = R.string.version, "1.0.1")
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
            items(list) { item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 16.dp)
                    ) {
                        Text(text = item, color = Color.Black)
                    }
                    SimpleDivider()
                }
            }
        }
    }
}