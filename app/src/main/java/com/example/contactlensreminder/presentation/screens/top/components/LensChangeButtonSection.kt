package com.example.contactlensreminder.presentation.screens.top

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.theme.CleanBlue

@Composable
fun LensChangeButtonSection(
    modifier: Modifier,
    isUsingContactLens: Boolean,
    changeIsUsingContactLens: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = changeIsUsingContactLens,
            modifier = Modifier.size(240.dp, 60.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = CleanBlue,
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
                    modifier = Modifier.size(30.dp, 30.dp)
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
                    modifier = Modifier.size(30.dp, 30.dp)
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