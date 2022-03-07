package io.github.rikuyu.contactlensreminder.ui.screens.top.components.on_boarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.ui.theme.CleanBlue

@Composable
fun AppWidgetOnBoarding(
    modifier: Modifier,
    title: String,
    description_1: String,
    description_2: String,
    image: Int
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_default),
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(text = title, color = CleanBlue, fontSize = 22.sp)
        }
        Text(text = description_1, fontSize = 14.sp)
    }
}