package io.github.rikuyu.contactlensreminder.presentation.screens.app_setting.terms_of_service_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.presentation.theme.CleanBlue
import io.github.rikuyu.contactlensreminder.presentation.util.Routes
import io.github.rikuyu.contactlensreminder.presentation.util.SimpleDivider
import io.github.rikuyu.contactlensreminder.presentation.util.SimpleSpacer

@Composable
fun TermsOfServiceScreen(navController: NavHostController) {
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
            titleSection(title = R.string.title_1)
            descriptionSection(description = R.string.content_1)
            item { SimpleSpacer(height = 14.dp) }
// 2条
            titleSection(title = R.string.title_2)
            descriptionSection(description = R.string.content_2)
            item { SimpleSpacer(height = 14.dp) }
// 3条
            titleSection(title = R.string.title_3)
            descriptionSection(description = R.string.content_3_1)
            descriptionSection(description = R.string.content_3_2)
            descriptionSection(description = R.string.content_3_3)
            item { SimpleSpacer(height = 14.dp) }
// 4条
            titleSection(title = R.string.title_4)
            descriptionSection(description = R.string.content_4)
            item { SimpleSpacer(height = 14.dp) }
// 5条
            titleSection(title = R.string.title_5)
            descriptionSection(description = R.string.content_5_1)
            descriptionSection(description = R.string.content_5_2)
            descriptionSection(description = R.string.content_5_2_1)
            descriptionSection(description = R.string.content_5_2_2)
            descriptionSection(description = R.string.content_5_2_3)
            descriptionSection(description = R.string.content_5_2_4)
            descriptionSection(description = R.string.content_5_3)
            item { SimpleSpacer(height = 14.dp) }
// 6条
            titleSection(title = R.string.title_6)
            descriptionSection(description = R.string.content_6_1)
            descriptionSection(description = R.string.content_6_2)
            descriptionSection(description = R.string.content_6_3)
            descriptionSection(description = R.string.content_6_4)
            item { SimpleSpacer(height = 14.dp) }
// 7条
            titleSection(title = R.string.title_7)
            descriptionSection(description = R.string.content_7)
            item { SimpleSpacer(height = 14.dp) }
// 8条
            titleSection(title = R.string.title_8)
            descriptionSection(description = R.string.content_8_1)
            descriptionSection(description = R.string.content_8_2)
            item { SimpleSpacer(height = 14.dp) }
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text(
                        text = stringResource(id = R.string.title_that_all),
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
            }
            item { SimpleSpacer(height = 14.dp) }
        }
    }
}

fun LazyListScope.descriptionSection(description: Int, fontSize: TextUnit = 14.sp) {
    item {
        Text(
            text = stringResource(id = description),
            fontSize = fontSize,
            color = Color.Black
        )
    }
}

fun LazyListScope.titleSection(title: Int, fontSize: TextUnit = 18.sp) {
    item {
        Text(
            text = stringResource(id = title),
            fontSize = fontSize,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
    item { SimpleSpacer(height = 4.dp) }
    item { SimpleDivider() }
    item { SimpleSpacer(height = 4.dp) }
}