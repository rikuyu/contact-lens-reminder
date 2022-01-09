package com.example.contactlensreminder.presentation.ui.screens.top

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.Routes
import com.example.contactlensreminder.presentation.ui.screens.appsetting.RemainingDaysBar
import com.example.contactlensreminder.presentation.ui.theme.CleanBlue
import com.example.contactlensreminder.presentation.ui.theme.LightBlue

@Composable
fun TopScreen(
    navController: NavController
) {

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    Column {
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
                .background(Color.White)
        )
        Row(
            modifier = Modifier.background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(7f))
            IconButton(onClick = { navController.navigate(Routes.APP_SETTING) }, modifier = Modifier.weight(1f)) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.size(30.dp, 30.dp)
            )
        }
            Spacer(modifier = Modifier.size(12.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            listOf("2Weeks", "1Month").forEachIndexed { index, item ->
                val selected = selectedIndex == index

                val shape = when (index) {
                    0 -> RoundedCornerShape(
                        topStart = 4.dp,
                        bottomStart = 4.dp,
                        topEnd = 0.dp,
                        bottomEnd = 0.dp
                    )
                    1 -> RoundedCornerShape(
                        topStart = 0.dp,
                        bottomStart = 0.dp,
                        topEnd = 4.dp,
                        bottomEnd = 4.dp
                    )
                    else -> CutCornerShape(0.dp)
                }

                val zIndex = if (selected) 1f else 0f

                val buttonModifier = when (index) {
                    0 -> Modifier.zIndex(zIndex)
                    else -> {
                        val offset = -1 * index
                        Modifier
                            .offset(x = offset.dp)
                            .zIndex(zIndex)
                    }
                }

                val border = if (selected) BorderStroke(
                    width = 1.dp,
                    color = CleanBlue
                ) else ButtonDefaults.outlinedBorder

                val colors =
                    ButtonDefaults.textButtonColors(backgroundColor = if (selected) CleanBlue else Color.Transparent)

                OutlinedButton(
                    onClick = { selectedIndex = index },
                    shape = shape,
                    colors = colors,
                    border = border,
                    modifier = buttonModifier.weight(1f)
                ) {
                    Text(text = item, color = if (selected) Color.White else CleanBlue)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth()
                .background(Color.White)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth()
                .background(Color.White)
        ) {
            RemainingDaysBar(days = 5f)
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.size(240.dp, 60.dp),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = LightBlue,
                    contentColor = Color.White,
                    disabledContentColor = Color.LightGray
                ),
                shape = RoundedCornerShape(30)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp, 30.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "レンズの交換", fontSize = 20.sp)
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { navController.navigate(Routes.LENS_SETTING) },
                modifier = Modifier.size(240.dp, 60.dp),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = LightBlue,
                    contentColor = Color.White,
                    disabledContentColor = Color.LightGray
                ),
                shape = RoundedCornerShape(30)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.water_drop),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp, 24.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "レンズの設定", fontSize = 18.sp)
            }
        }
        Spacer(modifier = Modifier.height(60.dp))
    }
}