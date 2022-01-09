package com.example.contactlensreminder.presentation.ui.screens.lenssetting

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.contactlensreminder.presentation.Routes

@Composable
fun LensSettingScreen(
    navController: NavController
) {
    val list = listOf("btn1", "btn2", "btn3")

    var selectedIndex by remember {
        mutableStateOf(0)
    }

    Column {
        Button(onClick = { navController.navigate(Routes.TOP) }) {
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            list.forEachIndexed { index, item ->
                val selected = selectedIndex == index

                val border = if (selected) BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colors.primary
                ) else ButtonDefaults.outlinedBorder

                val shape = when (index) {
                    0 -> RoundedCornerShape(
                        topStart = 4.dp,
                        bottomStart = 4.dp,
                        topEnd = 0.dp,
                        bottomEnd = 0.dp
                    )
                    list.size - 1 -> RoundedCornerShape(
                        topStart = 0.dp, bottomStart = 0.dp,
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

                val colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = if (selected) MaterialTheme.colors.primary.copy(alpha = 0.12f)
                    else MaterialTheme.colors.surface,
                    contentColor = if (selected) MaterialTheme.colors.primary else Color.DarkGray
                )
                OutlinedButton(
                    onClick = { selectedIndex = index },
                    border = border,
                    shape = shape,
                    colors = colors,
                    modifier = buttonModifier.weight(1f)
                ) {
                    Text(item)
                }
            }
        }
    }
}