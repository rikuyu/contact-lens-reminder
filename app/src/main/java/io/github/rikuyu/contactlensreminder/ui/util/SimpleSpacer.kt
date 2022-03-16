package io.github.rikuyu.contactlensreminder.ui.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun SimpleSpacer(height: Dp, color: Color = MaterialTheme.colors.surface) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(color)
    )
}