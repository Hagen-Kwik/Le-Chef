package com.uc.lechef.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.screens.LoginPage
import com.uc.lechef.screens.SignUpPage
import com.uc.lechef.screens.SplashScreen
import com.uc.lechef.screens.ViewModel.HomeScreenViewModel
import com.uc.lechef.screens.ViewModel.loginScreenViewModel
import com.uc.lechef.screens.ViewModel.sharedAllScreenViewModel
import com.uc.lechef.screens.ViewModel.signUpScreenViewModel
import com.uc.lechef.screens.botnavbar_pages.*

@Composable
fun AppNavigation(sharedViewModel: sharedAllScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
                  signUpviewModel: signUpScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
                  LoginviewModel: loginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
                  homeViewModel: HomeScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {


    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationEnum.SplashScreenActivity.name){

        composable(NavigationEnum.loginScreen.name){
            LoginPage(navController, LoginviewModel, sharedViewModel)
        }

        composable(NavigationEnum.SplashScreenActivity.name){
            SplashScreen(navController)
        }

        composable(NavigationEnum.signUpScreen.name){
            SignUpPage(navController, signUpviewModel)
        }

        composable(NavigationEnum.botnavbar.name){
            botnavbar(navController,sharedViewModel,homeViewModel)
        }

        composable(NavigationEnum.uploadRecipeOneScreen.name){
            uploadRecipeOneScreen(navController)
        }

        composable(NavigationEnum.uploadRecipeOneScreen.name){
            uploadRecipeTwoScreen(navController)
        }

        composable(NavigationEnum.searchByIngredientsScreen.name){
            searchByIngredientsScreen(navController, sharedViewModel)
        }

        composable(NavigationEnum.searchRecipesScreen.name){
            searchRecipesScreen(navController)
        }

        composable(NavigationEnum.DetailedRecipesScreen.name){
            DetailedRecipesScreen(navController)
        }


    }
}