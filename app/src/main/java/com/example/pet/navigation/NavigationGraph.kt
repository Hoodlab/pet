package com.example.pet.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pet.detail.DetailScreen
import com.example.pet.home.Home

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MyNavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = Home) {
            composable<Home> {
                Home(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this
                ) {
                    navController.navigate(Detail(it))
                }
            }

            composable<Detail> {
                val detail: Detail = it.toRoute()
                DetailScreen(
                    index = detail.id,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this,
                ) {
                    navController.navigateUp()
                }
            }
        }

    }
}