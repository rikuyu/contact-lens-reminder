package com.example.contactlensreminder.presentation.ui.screens.top

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.ui.screens.appsetting.RemainingDaysBar
import com.example.contactlensreminder.presentation.ui.theme.CleanBlue
import com.example.contactlensreminder.presentation.ui.theme.Gray
import com.example.contactlensreminder.presentation.util.Routes

@Composable
fun TopScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
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
            IconButton(
                onClick = { navController.navigate(Routes.APP_SETTING) },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    tint = Gray,
                    modifier = Modifier.size(36.dp, 36.dp)
                )
            }
            Spacer(modifier = Modifier.size(12.dp))
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
                    backgroundColor = CleanBlue,
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
                    backgroundColor = CleanBlue,
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
        Spacer(
            modifier = Modifier
                .height(60.dp)
                .background(Color.White)
        )
    }
}