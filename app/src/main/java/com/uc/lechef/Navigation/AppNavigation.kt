package com.uc.lechef.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.screens.LoginPage
import com.uc.lechef.screens.SignUpPage
import com.uc.lechef.screens.SplashScreen
import com.uc.lechef.screens.ViewModel.loginScreenViewModel
import com.uc.lechef.screens.ViewModel.signUpScreenViewModel
import com.uc.lechef.screens.botnavbar_pages.*

@Composable
fun AppNavigation(signUpviewModel: signUpScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
                  LoginviewModel: loginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationEnum.SplashScreenActivity.name){

        composable(NavigationEnum.loginScreen.name){
            LoginPage(navController, LoginviewModel)
        }

        composable(NavigationEnum.SplashScreenActivity.name){
            SplashScreen(navController)
        }

        composable(NavigationEnum.signUpScreen.name){
            SignUpPage(navController, signUpviewModel)
        }

        composable(NavigationEnum.HomeScreen.name){
            HomeScreen(navController)
        }

        composable(NavigationEnum.LikeRecipeScreen.name){
            LikedRecipesScreen(navController)
        }

        composable(NavigationEnum.MyRecipeScreen.name){
            MyRecipesScreen(navController)
        }

        composable(NavigationEnum.ProfileScreen.name){
            ProfileScreen(navController)
        }

        composable(NavigationEnum.SearchByIngridientsScreen.name){
//            HomeScreen(navController)
        }


    }
}