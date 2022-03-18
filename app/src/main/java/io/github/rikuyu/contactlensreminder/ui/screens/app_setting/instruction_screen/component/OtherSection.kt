package io.github.rikuyu.contactlensreminder.ui.screens.app_setting.instruction_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.ui.util.SimpleSpacer

@Composable
fun OtherSection(
    modifier: Modifier,
    textColor: Color = MaterialTheme.colors.onSurface,
    descFontSize: TextUnit = 14.sp
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    LazyColumn(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        item { TitleSection(title = stringResource(id = R.string.step_4_1)) }
        item {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.top_screen_4),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .width((screenWidth / 2).dp)
                        .padding(8.dp)
                )
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = stringResource(id = R.string.step_4_2),
                    fontSize = descFontSize,
                    color = textColor
                )
            }
        }
        item { TitleSection(title = stringResource(id = R.string.step_4_3)) }
        item {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.app_setting_screen),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.TopStart,
                    modifier = Modifier
                        .width((screenWidth / 2).dp)
                        .padding(8.dp)
                )
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = stringResource(id = R.string.step_4_4),
                    fontSize = descFontSize,
                    color = textColor
                )
            }
        }
        item { TitleSection(title = stringResource(id = R.string.step_4_5)) }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.light_dark_theme),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Text(
                modifier = Modifier.padding(10.dp),
                text = stringResource(id = R.string.step_4_6),
                fontSize = descFontSize,
                color = textColor
            )
        }
        item { TitleSection(title = stringResource(id = R.string.step_4_7)) }
        item {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.app_widget),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.TopStart,
                    modifier = Modifier
                        .width((screenWidth / 2).dp)
                        .padding(8.dp)
                )
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = stringResource(id = R.string.step_4_8),
                    fontSize = descFontSize,
                    color = textColor
                )
            }
        }
        item {
            SimpleSpacer(height = 20.dp, color = MaterialTheme.colors.background)
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = stringResource(id = R.string.step_4_9),
                fontSize = 16.sp,
                color = Color.Red
            )
            Text(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                text = stringResource(id = R.string.step_4_10),
                fontSize = descFontSize,
                color = textColor
            )
            Text(
                modifier = Modifier.padding(top = 4.dp, start = 10.dp, end = 10.dp),
                text = stringResource(id = R.string.step_4_11),
                fontSize = descFontSize,
                color = textColor
            )
            Text(
                modifier = Modifier.padding(top = 4.dp, start = 10.dp, end = 10.dp),
                text = stringResource(id = R.string.step_4_12),
                fontSize = descFontSize,
                color = textColor
            )
            SimpleSpacer(height = 20.dp, color = MaterialTheme.colors.background)
        }
    }
}