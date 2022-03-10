package io.github.rikuyu.contactlensreminder.ui.screens.top.components.on_boarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.rikuyu.contactlensreminder.BuildConfig
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.ui.theme.LightBlue
import io.github.rikuyu.contactlensreminder.ui.util.SimpleSpacer

@Composable
fun AnnounceVersionUpScreen(modifier: Modifier) {
    Column(
        modifier = modifier.background(LightBlue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_default),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.icon_default),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
        }
        SimpleSpacer(height = 20.dp, color = LightBlue)
        Text(
            text = stringResource(id = R.string.label_version_up, BuildConfig.VERSION_NAME),
            color = Color.White,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        SimpleSpacer(height = 30.dp, color = LightBlue)
        Text(
            text = stringResource(id = R.string.title_launch_app_widget),
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
        SimpleSpacer(height = 30.dp, color = LightBlue)
        Text(
            text = stringResource(id = R.string.title_abolish_app_icon_change),
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
        SimpleSpacer(height = 30.dp, color = LightBlue)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_default),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.icon_default),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
        }
    }
}