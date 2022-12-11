package com.uc.lechef.graphs

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.uc.lechef.R
import com.uc.lechef.screens.ScreenContent
import com.uc.lechef.screens.home.LikedRecipesScreen
import com.uc.lechef.screens.home.MainHomeScreen
import com.uc.lechef.screens.home.MyRecipesScreen
import com.uc.lechef.screens.home.ProfileScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = botbar.Home.route
    ) {
        composable(route = botbar.Home.route) {
            MainHomeScreen()
        }
        composable(route = botbar.MyRecipes.route) {
            MyRecipesScreen()
        }
        composable(route = botbar.LikedRecipes.route) {
            LikedRecipesScreen()
        }
        composable(route = botbar.Profile.route) {
            ProfileScreen()
        }

        detailsNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.Information.route
    ) {
        composable(route = DetailsScreen.Information.route) {
            ScreenContent(name = DetailsScreen.Information.route) {
                navController.navigate(DetailsScreen.Overview.route)
            }
        }
        composable(route = DetailsScreen.Overview.route) {
            ScreenContent(name = DetailsScreen.Overview.route) {
                navController.popBackStack(
                    route = DetailsScreen.Information.route,
                    inclusive = false
                )
            }
        }
    }
}

sealed class botbar(val id: Int, var route: String, @DrawableRes var Selectedicon:Int, @DrawableRes var unSelectedIcon:Int){
    object Home: botbar(0, "home", R.drawable.botnavbarhome, R.drawable.botnavbarhome)
    object MyRecipes: botbar(1, "myrecipes", R.drawable.botnavbarmyrecipes, R.drawable.botnavbarmyrecipes)
    object LikedRecipes: botbar(2, "likedrecipes", R.drawable.botnavbarlikedrecipe, R.drawable.botnavbarlikedrecipe)
    object Profile: botbar(3, "profile", R.drawable.botnavbarprofile, R.drawable.botnavbarprofile)
}


sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen(route = "INFORMATION")
    object Overview : DetailsScreen(route = "OVERVIEW")
}

