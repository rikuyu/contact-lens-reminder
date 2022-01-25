package com.example.contactlensreminder.presentation.screens.app_setting.instruction_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.util.SimpleSpacer

@Composable
fun SecondStep(modifier: Modifier) {
    LazyColumn(modifier.fillMaxSize()) {
        item { TitleSection(title = stringResource(id = R.string.step_2_1)) }
        item {
            Section(
                painter = painterResource(id = R.drawable.day),
                description = stringResource(id = R.string.step_2_2)
            )
        }
        item {
            TitleSection(title = stringResource(id = R.string.step_2_3))
        }
        item {
            Section(
                painter = painterResource(id = R.drawable.notification),
                description = stringResource(id = R.string.step_2_4)
            )
        }
        item { TitleSection(title = stringResource(id = R.string.step_2_5)) }
        item {
            Section(
                painter = painterResource(id = R.drawable.power),
                description = stringResource(id = R.string.step_2_6)
            )
        }
        item { TitleSection(title = stringResource(id = R.string.step_2_7)) }
        item {
            Section(
                painter = painterResource(id = R.drawable.save_btn),
                description = stringResource(id = R.string.step_2_8)
            )
        }
        item { SimpleSpacer(height = 20.dp) }
    }
}

@Composable
fun Section(
    painter: Painter,
    description: String,
    textColor: Color = Color.Black,
    fontSize: TextUnit = 14.sp
) {
    Column {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
            text = description,
            fontSize = fontSize,
            color = textColor
        )
    }
}