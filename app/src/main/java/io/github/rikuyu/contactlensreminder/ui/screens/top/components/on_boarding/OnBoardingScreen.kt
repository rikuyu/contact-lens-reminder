package io.github.rikuyu.contactlensreminder.ui.screens.top.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import io.github.rikuyu.contactlensreminder.R
import io.github.rikuyu.contactlensreminder.domain.model.OnBoardingPage
import io.github.rikuyu.contactlensreminder.ui.theme.CleanBlue
import io.github.rikuyu.contactlensreminder.ui.theme.PaleBlue
import io.github.rikuyu.contactlensreminder.ui.util.Routes

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {

    val onBoardingPages = listOf(
        OnBoardingPage(
            title = stringResource(id = R.string.title_launch_app_widget),
            description = "First Screen",
            image = R.drawable.app_icons
        ),
        OnBoardingPage(
            title = stringResource(id = R.string.title_omit_app_icon_change),
            description = "Second Screen",
            image = R.drawable.top_screen_2
        )
    )

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
            count = onBoardingPages.size,
            state = pagerState
        ) { position ->
            PagerScreen(
                onBoardingPage = onBoardingPages[position],
                position
            ) { navController.navigate(Routes.TOP) }
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

@Composable
fun PagerScreen(
    onBoardingPage: OnBoardingPage,
    position: Int,
    navigate: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = onBoardingPage.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Image(
                modifier = Modifier.size(80.dp, 100.dp),
                painter = painterResource(id = onBoardingPage.image),
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(top = 20.dp),
                text = onBoardingPage.description,
                fontSize = 14.sp
            )
        }
        if (position == 1) {
            Button(
                onClick = navigate,
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = CleanBlue,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
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
}