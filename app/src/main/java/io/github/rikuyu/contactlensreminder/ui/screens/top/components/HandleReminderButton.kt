package io.github.rikuyu.contactlensreminder.ui.screens.top.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.ui.theme.ColorPalette

@Composable
fun HandleReminderButton(
    modifier: Modifier,
    color: Color = MaterialTheme.colors.primary,
    isUsingContactLens: Boolean,
    lensRemainingDays: Int,
    startReminder: (Boolean) -> Unit,
    showDialog: () -> Unit,
) {
    val onClick: (Boolean) -> Unit = if (isUsingContactLens) {
        { showDialog() }
    } else {
        { startReminder(!it) }
    }

    var playedRotate by remember { mutableStateOf(false) }

    val rotateAnimation by animateFloatAsState(
        targetValue = if (playedRotate) 0f else -360f,
        animationSpec = tween(durationMillis = 1200)
    )

    var playedScale by remember { mutableStateOf(false) }

    val scaleAnimation by animateFloatAsState(
        targetValue = if (playedScale) 1.2f else 1.0f,
        animationSpec = tween(
            durationMillis = 950
        ),
        finishedListener = { playedScale = false }
    )

    LaunchedEffect(key1 = true) {
        playedRotate = true
        playedScale = true
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { onClick(isUsingContactLens) },
            modifier = Modifier.size(236.dp, 60.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = if (lensRemainingDays < 1) ColorPalette.Red.secondaryRed else color,
                contentColor = Color.White,
                disabledContentColor = Color.LightGray
            ),
            shape = RoundedCornerShape(30)
        ) {
            if (isUsingContactLens) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp, 30.dp)
                        .rotate(rotateAnimation)
                        .scale(if (lensRemainingDays == 0) scaleAnimation else 1.0f)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = stringResource(id = R.string.lens_change),
                    fontSize = 20.sp
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_start),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp, 30.dp)
                        .graphicsLayer(
                            translationX = animateFloatAsState(
                                if (playedRotate) 0f
                                else -30f,
                                tween(1000)
                            ).value
                        )
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = stringResource(id = R.string.start),
                    fontSize = 20.sp
                )
            }
        }
    }
}