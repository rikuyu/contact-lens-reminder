package io.github.rikuyu.contactlensreminder.ui.screens.top.components

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
import androidx.navigation.NavController
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.ui.theme.SkyBlue
import io.github.rikuyu.contactlensreminder.ui.util.Routes

@Composable
fun LensSettingButtonSection(
    modifier: Modifier,
    isUsingContactLens: Boolean,
    navController: NavController
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                navController.navigate(
                    if (isUsingContactLens)
                        Routes.NOTIFICATION_SETTING
                    else
                        Routes.LENS_SETTING
                )
            },
            modifier = Modifier.size(240.dp, 60.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = SkyBlue,
                contentColor = Color.White,
                disabledContentColor = Color.White
            ),
            shape = RoundedCornerShape(30)
        ) {
            Icon(
                painter = painterResource(
                    if (isUsingContactLens)
                        R.drawable.ic_notify
                    else
                        R.drawable.ic_water_drop
                ),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp, 24.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = stringResource(
                    if (isUsingContactLens)
                        R.string.notification_setting_label
                    else
                        R.string.lens_setting_label
                ),
                fontSize = 18.sp
            )
        }
    }
}