package com.example.contactlensreminder.presentation.screens.top.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.theme.SkyBlue

@Composable
fun LensSettingButtonSection(
    modifier: Modifier,
    isUsingContactLens: Boolean,
    showAlertToast: () -> Unit,
    navigate: () -> Unit
) {
    val onClick: () -> Unit = {
        if (isUsingContactLens) {
            showAlertToast()
        } else {
            navigate()
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.size(240.dp, 60.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = if (!isUsingContactLens) SkyBlue else Color.LightGray,
                contentColor = Color.White,
                disabledContentColor = Color.White
            ),
            shape = RoundedCornerShape(30)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.water_drop),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp, 24.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = stringResource(id = R.string.lens_setting),
                fontSize = 18.sp
            )
        }
    }
}