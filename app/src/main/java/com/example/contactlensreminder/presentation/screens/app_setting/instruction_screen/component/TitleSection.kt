package com.example.contactlensreminder.presentation.screens.app_setting.instruction_screen.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.theme.SkyBlue

@Composable
fun TitleSection(
    title: String,
    painter: Painter = painterResource(id = R.drawable.water_drop),
    textColor: Color = Color.Black,
    fontSize: TextUnit = 16.sp
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 6.dp)) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = SkyBlue,
            modifier = Modifier
                .size(30.dp)
                .padding(start = 10.dp, end = 4.dp)
        )
        Text(
            text = title,
            fontSize = fontSize,
            color = textColor,
            fontWeight = FontWeight.Medium
        )
    }
}