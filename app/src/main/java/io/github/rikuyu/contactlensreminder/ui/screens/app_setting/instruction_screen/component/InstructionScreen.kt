package io.github.rikuyu.contactlensreminder.ui.screens.app_setting.instruction_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import io.github.rikuyu.contactlensreminder.R
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
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                contentColor = Color.White,
                backgroundColor = MaterialTheme.colors.primary
            )
        }
    ) {
        val context = LocalContext.current

        val pages = listOf(
            context.getString(R.string.help_1_page),
            context.getString(R.string.help_2_page),
            context.getString(R.string.help_3_page),
            context.getString(R.string.other_page)
        )

        Column(Modifier.fillMaxSize()) {
            val coroutineScope = rememberCoroutineScope()
            val pagerState = rememberPagerState()

            TabRow(
                selectedTabIndex = pagerState.currentPage,
                contentColor = Color.White,
                backgroundColor = MaterialTheme.colors.primaryVariant,
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
                    .background(MaterialTheme.colors.primary)
                    .weight(1f)
                    .fillMaxWidth()
            ) { page ->
                when (page) {
                    0 -> FirstStep(Modifier.padding(5.dp))
                    1 -> SecondStep(Modifier.padding(5.dp))
                    2 -> ThirdStep(Modifier.padding(5.dp))
                    3 -> OtherSection(Modifier.padding(5.dp))
                }
            }
        }
    }
}