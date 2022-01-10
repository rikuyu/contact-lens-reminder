package com.example.contactlensreminder.presentation.ui.screens.lenssetting

import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.chargemap.compose.numberpicker.ListItemPicker
import com.example.contactlensreminder.presentation.ui.theme.CleanBlue

@Composable
fun LensPowerPicker(
    modifier: Modifier = Modifier,
    label: (Double) -> String = {
        it.toString()
    },
    value: Double,
    onValueChange: (Double) -> Unit,
    dividersColor: Color = CleanBlue,
    range: List<Double>,
    textStyle: TextStyle = LocalTextStyle.current.copy(color = Color.Black),
) {
    ListItemPicker(
        modifier = modifier,
        label = label,
        value = value,
        onValueChange = onValueChange,
        dividersColor = dividersColor,
        list = range.toList(),
        textStyle = textStyle
    )
}

@Composable
fun DaysPicker(
    modifier: Modifier = Modifier,
    label: (Int) -> String = {
        it.toString()
    },
    value: Int,
    onValueChange: (Int) -> Unit,
    dividersColor: Color = CleanBlue,
    range: Iterable<Int>,
    textStyle: TextStyle = LocalTextStyle.current.copy(color = Color.Black),
) {
    ListItemPicker(
        modifier = modifier,
        label = label,
        value = value,
        onValueChange = onValueChange,
        dividersColor = dividersColor,
        list = range.toList(),
        textStyle = textStyle
    )
}