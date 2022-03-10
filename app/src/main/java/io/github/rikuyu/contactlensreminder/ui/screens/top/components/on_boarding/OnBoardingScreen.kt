package io.github.rikuyu.contactlensreminder.ui.screens.top.components.on_boarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
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
            count = 3,
            state = pagerState
        ) { position ->
            when (position) {
                0 -> AnnounceVersionUpScreen(modifier = Modifier.fillMaxSize())
                1 -> LaunchAppWidget(modifier = Modifier.fillMaxSize())
                2 -> AbolishChangeIcon(modifier = Modifier.fillMaxSize()) {
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