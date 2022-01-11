package com.example.contactlensreminder.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun SimpleSpacer(height: Dp, color: Color = Color.White) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(color)
    )
}