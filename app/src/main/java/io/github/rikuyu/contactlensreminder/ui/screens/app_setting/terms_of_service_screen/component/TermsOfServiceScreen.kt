package io.github.rikuyu.contactlensreminder.ui.screens.app_setting.terms_of_service_screen.component

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
import io.github.rikuyu.contactlensreminder.ui.util.SimpleDivider
import io.github.rikuyu.contactlensreminder.ui.util.SimpleSpacer

@Composable
fun TermsOfServiceScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.terms_of_service))
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(horizontal = 10.dp)
        ) {
            item { SimpleSpacer(height = 12.dp, color = MaterialTheme.colors.background) }
// 1条
            titleSection(title = R.string.title_1)
            descriptionSection(description = R.string.content_1)
            item { SimpleSpacer(height = 32.dp, color = MaterialTheme.colors.background) }
// 2条
            titleSection(title = R.string.title_2)
            descriptionSection(description = R.string.content_2)
            item { SimpleSpacer(height = 32.dp, color = MaterialTheme.colors.background) }
// 3条
            titleSection(title = R.string.title_3)
            descriptionSection(description = R.string.content_3_1)
            descriptionSection(description = R.string.content_3_2)
            descriptionSection(description = R.string.content_3_3)
            item { SimpleSpacer(height = 32.dp, color = MaterialTheme.colors.background) }
// 4条
            titleSection(title = R.string.title_4)
            descriptionSection(description = R.string.content_4)
            item { SimpleSpacer(height = 32.dp, color = MaterialTheme.colors.background) }
// 5条
            titleSection(title = R.string.title_5)
            descriptionSection(description = R.string.content_5_1)
            descriptionSection(description = R.string.content_5_2)
            descriptionSection(description = R.string.content_5_2_1)
            descriptionSection(description = R.string.content_5_2_2)
            descriptionSection(description = R.string.content_5_2_3)
            descriptionSection(description = R.string.content_5_2_4)
            descriptionSection(description = R.string.content_5_3)
            item { SimpleSpacer(height = 32.dp, color = MaterialTheme.colors.background) }
// 6条
            titleSection(title = R.string.title_6)
            descriptionSection(description = R.string.content_6_1)
            descriptionSection(description = R.string.content_6_2)
            descriptionSection(description = R.string.content_6_3)
            descriptionSection(description = R.string.content_6_4)
            item { SimpleSpacer(height = 32.dp, color = MaterialTheme.colors.background) }
// 7条
            titleSection(title = R.string.title_7)
            descriptionSection(description = R.string.content_7)
            item { SimpleSpacer(height = 32.dp, color = MaterialTheme.colors.background) }
// 8条
            titleSection(title = R.string.title_8)
            descriptionSection(description = R.string.content_8_1)
            descriptionSection(description = R.string.content_8_2)
            item { SimpleSpacer(height = 32.dp, color = MaterialTheme.colors.background) }
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text(
                        text = stringResource(id = R.string.title_that_all),
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }
            item { SimpleSpacer(height = 14.dp, color = MaterialTheme.colors.background) }
        }
    }
}

fun LazyListScope.descriptionSection(description: Int, fontSize: TextUnit = 14.sp) {
    item {
        Text(
            text = stringResource(id = description),
            fontSize = fontSize,
            color = MaterialTheme.colors.onSurface
        )
    }
}

fun LazyListScope.titleSection(title: Int, fontSize: TextUnit = 18.sp) {
    item {
        Text(
            text = stringResource(id = title),
            fontSize = fontSize,
            color = MaterialTheme.colors.onSurface,
            fontWeight = FontWeight.Medium
        )
    }
    item { SimpleSpacer(height = 4.dp, color = MaterialTheme.colors.background) }
    item { SimpleDivider() }
    item { SimpleSpacer(height = 4.dp, color = MaterialTheme.colors.background) }
}