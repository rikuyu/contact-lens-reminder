package com.example.contactlensreminder.presentation.screens.app_setting.terms_of_service.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.theme.CleanBlue
import com.example.contactlensreminder.presentation.util.Routes
import com.example.contactlensreminder.presentation.util.SimpleDivider
import com.example.contactlensreminder.presentation.util.SimpleSpacer

@Composable
fun TermsOfService(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.terms_of_service))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Routes.APP_SETTING) }) {
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
                .background(Color.White)
                .padding(horizontal = 10.dp)
        ) {
            item { SimpleSpacer(height = 12.dp) }
            // 1条
            item {
                Text(
                    text = stringResource(id = R.string.title_1),
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
            item { SimpleSpacer(height = 4.dp) }
            item { SimpleDivider() }
            item { SimpleSpacer(height = 4.dp) }
            item {
                Text(
                    text = stringResource(id = R.string.content_1),
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            item { SimpleSpacer(height = 14.dp) }
            // 2条
            item {
                Text(
                    text = stringResource(id = R.string.title_2),
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
            item { SimpleSpacer(height = 4.dp) }
            item { SimpleDivider() }
            item { SimpleSpacer(height = 4.dp) }
            item {
                Text(
                    text = stringResource(id = R.string.content_2),
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            item { SimpleSpacer(height = 14.dp) }
            // 3条
            item {
                Text(
                    text = stringResource(id = R.string.title_3),
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
            item { SimpleSpacer(height = 4.dp) }
            item { SimpleDivider() }
            item { SimpleSpacer(height = 4.dp) }
            item {
                Text(
                    text = stringResource(id = R.string.content_3_1),
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.content_3_2),
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.content_3_3),
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            item { SimpleSpacer(height = 14.dp) }
            // 4条
            item {
                Text(
                    text = stringResource(id = R.string.title_4),
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
            item { SimpleSpacer(height = 4.dp) }
            item { SimpleDivider() }
            item { SimpleSpacer(height = 4.dp) }
            item {
                Text(
                    text = stringResource(id = R.string.content_4),
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            item { SimpleSpacer(height = 14.dp) }
            // 5条
            item {
                Text(
                    text = stringResource(id = R.string.title_5),
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
            item { SimpleSpacer(height = 4.dp) }
            item { SimpleDivider() }
            item { SimpleSpacer(height = 4.dp) }
            item {
                Text(
                    text = stringResource(id = R.string.content_5_1),
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.content_5_2),
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.content_5_2_1),
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.content_5_2_2),
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.content_5_2_3),
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.content_5_2_4),
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.content_5_3),
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            item { SimpleSpacer(height = 14.dp) }
            // 6条
            item {
                Text(
                    text = stringResource(id = R.string.title_6),
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
            item { SimpleSpacer(height = 4.dp) }
            item { SimpleDivider() }
            item { SimpleSpacer(height = 4.dp) }
            item {
                Text(
                    text = stringResource(id = R.string.content_6_1),
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.content_6_2),
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.content_6_3),
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.content_6_4),
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            item { SimpleSpacer(height = 14.dp) }
            // 7条
            item {
                Text(
                    text = stringResource(id = R.string.title_7),
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
            item { SimpleSpacer(height = 4.dp) }
            item { SimpleDivider() }
            item { SimpleSpacer(height = 4.dp) }
            item {
                Text(
                    text = stringResource(id = R.string.content_7),
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            item { SimpleSpacer(height = 14.dp) }
            // 8条
            item {
                Text(
                    text = stringResource(id = R.string.title_8),
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
            item { SimpleSpacer(height = 4.dp) }
            item { SimpleDivider() }
            item { SimpleSpacer(height = 4.dp) }
            item {
                Text(
                    text = stringResource(id = R.string.content_8_1),
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.content_8_2),
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.content_8_3),
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            item { SimpleSpacer(height = 14.dp) }
            item {
                Text(
                    text = stringResource(id = R.string.title_that_all),
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            item { SimpleSpacer(height = 14.dp) }
        }
    }
}