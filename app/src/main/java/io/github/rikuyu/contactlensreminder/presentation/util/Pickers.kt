package io.github.rikuyu.contactlensreminder.presentation.util

import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.chargemap.compose.numberpicker.ListItemPicker
import io.github.rikuyu.contactlensreminder.presentation.theme.CleanBlue

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