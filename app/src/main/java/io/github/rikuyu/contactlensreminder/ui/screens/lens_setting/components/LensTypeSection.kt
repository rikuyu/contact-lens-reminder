package io.github.rikuyu.contactlensreminder.ui.screens.lens_setting.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.rikuyu.contactlensreminder.R

@Composable
fun LensTypeSection(
    modifier: Modifier,
    textColor: Color = MaterialTheme.colors.onSurface,
    fontSize: TextUnit = 18.sp,
    lensType: Int,
    onClick: (Int) -> Unit,
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .padding(top = 14.dp, bottom = 14.dp, end = 12.dp, start = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.period),
            color = textColor,
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
            fontSize = fontSize
        )
        listOf(
            stringResource(id = R.string.two_weeks),
            stringResource(id = R.string.one_month),
            stringResource(id = R.string.other)
        ).forEachIndexed { index, text ->
            val selected = lensType == index
            OutlinedButton(
                onClick = { onClick(index) },
                shape = when (index) {
                    0 -> RoundedCornerShape(
                        topStart = 4.dp,
                        bottomStart = 4.dp,
                        topEnd = 0.dp,
                        bottomEnd = 0.dp
                    )
                    2 -> RoundedCornerShape(
                        topStart = 0.dp,
                        bottomStart = 0.dp,
                        topEnd = 4.dp,
                        bottomEnd = 4.dp
                    )
                    else -> CutCornerShape(0.dp)
                },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = if (selected) MaterialTheme.colors.primary else Color.Transparent
                ),
                modifier = Modifier.padding(vertical = 2.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colors.primary
                )
            ) {
                Text(
                    text = text,
                    color = if (selected) Color.White else MaterialTheme.colors.primary,
                    modifier = Modifier.padding(vertical = 2.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}