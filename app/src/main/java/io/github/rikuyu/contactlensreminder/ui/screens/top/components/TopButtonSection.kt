package io.github.rikuyu.contactlensreminder.ui.screens.top.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.rikuyu.contactlensreminder.R

@Composable
fun TopButtonSection(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    themeButtonEvent: (Boolean) -> Unit,
    helpButtonEvent: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(horizontal = 8.dp, vertical = 10.dp)
    ) {
        Icon(
            painter = painterResource(id = if (isDarkTheme) R.drawable.ic_light_mode else R.drawable.ic_dark_mode),
            contentDescription = null,
            tint = if (isDarkTheme) Color.White else Color.Black,
            modifier = Modifier
                .weight(1f)
                .size(30.dp, 30.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(
                        color = MaterialTheme.colors.primary,
                        bounded = false,
                        radius = 28.dp
                    )
                ) { themeButtonEvent(!isDarkTheme) }
        )
        Spacer(modifier = Modifier.weight(7f))
        Icon(
            painter = painterResource(id = R.drawable.ic_help),
            contentDescription = null,
            tint = MaterialTheme.colors.primaryVariant,
            modifier = Modifier
                .weight(1f)
                .size(34.dp, 34.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(
                        color = MaterialTheme.colors.primary,
                        bounded = false,
                        radius = 28.dp
                    )
                ) { helpButtonEvent() }
        )
    }
}