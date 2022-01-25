package com.example.contactlensreminder.presentation.screens.app_setting.instruction_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.util.SimpleSpacer

@Composable
fun ThirdStep(
    modifier: Modifier,
    textColor: Color = Color.Black,
    descFontSize: TextUnit = 14.sp
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    LazyColumn(modifier.fillMaxSize()) {
        item { TitleSection(title = stringResource(id = R.string.step_3_1)) }
        item {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.top_screen_3),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .width((screenWidth / 2).dp)
                        .padding(8.dp)
                )
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = stringResource(id = R.string.step_3_2),
                    fontSize = descFontSize,
                    color = textColor
                )
            }
        }
        item { TitleSection(title = stringResource(id = R.string.step_3_3)) }
        item {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.top_screen_2),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.TopStart,
                    modifier = Modifier
                        .width((screenWidth / 2).dp)
                        .padding(8.dp)
                )
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = stringResource(id = R.string.step_3_4),
                    fontSize = descFontSize,
                    color = textColor
                )
            }
        }
        item {
            Text(
                modifier = Modifier.padding(top = 16.dp, start = 8.dp),
                text = stringResource(id = R.string.step_3_5),
                fontSize = descFontSize,
                color = textColor
            )
        }
        item { SimpleSpacer(height = 20.dp) }
    }
}