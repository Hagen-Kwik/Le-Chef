package com.uc.lechef.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.screens.LoginPage
import com.uc.lechef.screens.SignUpPage
import com.uc.lechef.screens.SplashScreen
import com.uc.lechef.screens.ViewModel.signUpScreenViewModel

@Composable
fun AppNavigation(viewModel: signUpScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationEnum.SplashScreenActivity.name){

        composable(NavigationEnum.loginScreen.name){
            LoginPage(navController)
        }

        composable(NavigationEnum.SplashScreenActivity.name){
            SplashScreen(navController)
        }

        composable(NavigationEnum.signUpScreen.name){
            SignUpPage(navController, viewModel)
        }
    }
}