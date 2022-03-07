package io.github.rikuyu.contactlensreminder.ui.screens.top.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.domain.model.OnBoardingPage
import io.github.rikuyu.contactlensreminder.ui.screens.top.components.on_boarding.AbolishChangeIcon
import io.github.rikuyu.contactlensreminder.ui.screens.top.components.on_boarding.LaunchAppWidget
import io.github.rikuyu.contactlensreminder.ui.theme.CleanBlue
import io.github.rikuyu.contactlensreminder.ui.theme.PaleBlue
import io.github.rikuyu.contactlensreminder.ui.util.Routes

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {

    val pagerState = rememberPagerState()

    Box(
        modifier = Modifier
            .background(PaleBlue)
            .padding(vertical = 30.dp, horizontal = 20.dp)
            .fillMaxSize()
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White),
            count = 2,
            state = pagerState
        ) { position ->
            when (position) {
                0 -> LaunchAppWidget(modifier = Modifier.fillMaxSize())
                1 -> AbolishChangeIcon(modifier = Modifier.fillMaxSize()) {
                    navController.navigate(Routes.TOP)
                }
            }
        }

        HorizontalPagerIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp),
            pagerState = pagerState,
            activeColor = CleanBlue,
            inactiveColor = Color.LightGray
        )
    }
}