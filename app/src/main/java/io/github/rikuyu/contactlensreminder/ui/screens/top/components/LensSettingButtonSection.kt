package io.github.rikuyu.contactlensreminder.ui.screens.top.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.rikuyu.contactlensreminder.R

@Composable
fun LensSettingButtonSection(
    modifier: Modifier,
    color: Color = MaterialTheme.colors.primaryVariant,
    isUsingContactLens: Boolean,
    showAlertToast: () -> Unit,
    navigate: () -> Unit,
) {
    var isFirstTap by remember { mutableStateOf(true) }

    var animate by remember { mutableStateOf(true) }

    val onClick: () -> Unit = {
        if (isUsingContactLens) {
            if (isFirstTap) showAlertToast()
            isFirstTap = false
        } else {
            navigate()
        }
    }

    LaunchedEffect(key1 = true) { animate = false }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.size(240.dp, 60.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = if (!isUsingContactLens) color else Color.LightGray,
                contentColor = Color.White,
                disabledContentColor = Color.White
            ),
            shape = RoundedCornerShape(30)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_water_drop),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp, 24.dp)
                    .graphicsLayer(translationY = animateFloatAsState(if (animate) -30f else 0f, tween(1000)).value)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = stringResource(id = R.string.lens_setting),
                fontSize = 18.sp
            )
        }
    }
}