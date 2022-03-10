package io.github.rikuyu.contactlensreminder.ui.screens.top.components.on_boarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.ui.theme.CleanBlue
import io.github.rikuyu.contactlensreminder.ui.util.SimpleSpacer

@Composable
fun AbolishChangeIcon(
    modifier: Modifier,
    navigate: () -> Unit
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                        .size(40.dp)
                        .padding(start = 4.dp, end = 4.dp)
                )
                Text(
                    text = stringResource(id = R.string.title_abolish_app_icon_change),
                    color = CleanBlue,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Image(
                painter = painterResource(id = R.drawable.change_app_icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 12.dp)
                    .clip(RoundedCornerShape(10))
            )
            SimpleSpacer(height = 10.dp)
            Section(text = R.string.description_1_abolish_app_icon_change)
            SimpleSpacer(height = 20.dp)
            Section(text = R.string.description_2_abolish_app_icon_change)
            SimpleSpacer(height = 20.dp)
            Section(text = R.string.description_3_abolish_app_icon_change)
        }
        Button(
            onClick = navigate,
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = CleanBlue,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp, vertical = 10.dp),
            shape = RoundedCornerShape(20)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = stringResource(id = R.string.btn_start),
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun Section(text: Int) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_default),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .padding(end = 4.dp)
        )
        Text(
            text = stringResource(id = text),
            fontSize = 16.sp
        )
    }
}