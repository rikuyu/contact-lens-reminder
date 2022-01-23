package com.example.contactlensreminder.presentation.screens.app_setting.instruction_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.contactlensreminder.R
import com.example.contactlensreminder.presentation.theme.CleanBlue
import com.example.contactlensreminder.presentation.theme.SkyBlue
import com.example.contactlensreminder.presentation.util.Routes
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun InstructionScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.help))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Routes.APP_SETTING) }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                contentColor = Color.White,
                backgroundColor = CleanBlue
            )
        }
    ) {
        val context = LocalContext.current

        val pages = remember {
            listOf(
                context.getString(R.string.help_1),
                context.getString(R.string.help_2),
                context.getString(R.string.help_3)
            )
        }

        Column(Modifier.fillMaxSize()) {
            val coroutineScope = rememberCoroutineScope()
            val pagerState = rememberPagerState()

            TabRow(
                selectedTabIndex = pagerState.currentPage,
                contentColor = Color.White,
                backgroundColor = SkyBlue,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                    )
                }
            ) {
                pages.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }
            HorizontalPager(
                count = pages.size,
                state = pagerState,
                modifier = Modifier
                    .background(CleanBlue)
                    .weight(1f)
                    .fillMaxWidth()
            ) { page ->
                when (page) {
                    0 -> Text("0")
                    1 -> Text("1")
                    2 -> Text("2")
                }
            }
        }
    }
}