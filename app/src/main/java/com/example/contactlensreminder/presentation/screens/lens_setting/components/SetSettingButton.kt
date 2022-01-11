package com.example.contactlensreminder.presentation.screens.lens_setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import com.example.contactlensreminder.presentation.theme.CleanBlue

@Composable
fun SetSettingButton(
    modifier: Modifier,
    fontSize: TextUnit = 18.sp,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .background(Color.White)
            .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = CleanBlue,
                contentColor = Color.White,
                disabledContentColor = Color.LightGray
            ),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp, vertical = 10.dp),
            shape = RoundedCornerShape(20)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = stringResource(id = R.string.ok),
                fontSize = fontSize
            )
        }
    }
}