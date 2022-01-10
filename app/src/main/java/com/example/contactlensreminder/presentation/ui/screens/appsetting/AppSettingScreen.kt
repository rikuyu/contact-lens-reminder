package com.example.contactlensreminder.presentation.ui.screens.appsetting


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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.contactlensreminder.presentation.ui.theme.CleanBlue
import com.example.contactlensreminder.presentation.ui.theme.SmoothGray
import com.example.contactlensreminder.presentation.util.Routes
import com.example.contactlensreminder.presentation.util.SimpleDivider

@Composable
fun AppSettingScreen(
    navController: NavController
) {

    val list = listOf("プライバシーポリシー", "ヘルプ", "利用規約", "バージョン 1.0.1")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "このアプリについて")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Routes.TOP) }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
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
            items(list) { item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 12.dp)
                    ) {
                        Text(text = item, color = Color.Black)
                    }
                    SimpleDivider()
                }
            }
        }
    }
}