package com.example.contactlensreminder.presentation.ui.screens.lens_setting

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
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
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.ui.theme.CleanBlue

@Composable
fun SetLensTypeSection(
    modifier: Modifier,
    textColor: Color = Color.Black,
    fontSize: TextUnit = 18.sp,
    lensType: Int,
    showDecideLensPeriodForm: (index: Int) -> Unit
) {
    Row(
        modifier = modifier
            .background(Color.White)
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
        ).forEachIndexed { index, item ->
            val selected = lensType == index

            val shape = when (index) {
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
            }
            OutlinedButton(
                onClick = { showDecideLensPeriodForm(index) },
                shape = shape,
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = if (selected) CleanBlue else Color.Transparent
                ),
                modifier = Modifier.padding(vertical = 2.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = CleanBlue
                )
            ) {
                Text(
                    text = item,
                    color = if (selected) Color.White else CleanBlue,
                    modifier = Modifier.padding(vertical = 2.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}