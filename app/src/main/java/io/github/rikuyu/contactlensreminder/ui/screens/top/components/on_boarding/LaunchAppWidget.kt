package io.github.rikuyu.contactlensreminder.ui.screens.top.components.on_boarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.ui.util.SimpleSpacer

@Composable
fun LaunchAppWidget(modifier: Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_default),
                contentDescription = null,
                modifier = Modifier
                    .size(46.dp)
                    .padding(end = 4.dp)
            )
            Text(
                text = stringResource(id = R.string.title_launch_app_widget),
                color = MaterialTheme.colors.primary, fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Image(
            painter = painterResource(id = R.drawable.app_widget),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(10))
        )
        SimpleSpacer(height = 14.dp, color = MaterialTheme.colors.background)
        Section(text = R.string.description_1_launch_app__widget)
        SimpleSpacer(height = 20.dp, color = MaterialTheme.colors.background)
        Section(text = R.string.description_2_launch_app__widget)
        SimpleSpacer(height = 20.dp, color = MaterialTheme.colors.background)
        Section(text = R.string.description_3_launch_app__widget)
        SimpleSpacer(height = 20.dp, color = MaterialTheme.colors.background)
        Section(text = R.string.description_4_launch_app__widget)
    }
}