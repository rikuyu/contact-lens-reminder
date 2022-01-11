package com.example.contactlensreminder.presentation.ui.screens.lens_setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.util.SimpleDivider

@Composable
fun SetChangeLensDaySection(
    modifier: Modifier = Modifier,
    textColor: Color = Color.Black,
    fontSize: TextUnit = 18.sp
) {
    var pickerValue by remember { mutableStateOf(0) }

    Column(
        modifier = modifier.background(Color.White)
    ) {
        Row(
            modifier = modifier.padding(all = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.lens_change_period),
                color = textColor,
                modifier = modifier
                    .weight(1f)
                    .padding(12.dp),
                fontSize = fontSize
            )
            DaysPicker(
                value = pickerValue,
                onValueChange = { pickerValue = it },
                range = 1..31
            )
        }
        SimpleDivider()
    }
}