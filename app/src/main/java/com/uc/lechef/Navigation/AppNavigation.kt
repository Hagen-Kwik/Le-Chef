package com.uc.lechef.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.screens.LoginPage
import com.uc.lechef.screens.SignUpPage
import com.uc.lechef.screens.SplashScreen
import com.uc.lechef.screens.ViewModel.*
import com.uc.lechef.screens.botnavbar_pages.*

@Composable
fun AppNavigation(sharedViewModel: sharedAllScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
                  signUpviewModel: signUpScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
                  LoginviewModel: loginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
                  homeViewModel: HomeScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
                  UploadRecipeViewModel: UploadRecipeScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
                  MyrecipeViewModel: MyrecipeScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
                  LikedRecipeViewModel: LikedRecipeScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
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
            botnavbar(navController,sharedViewModel,homeViewModel, MyrecipeViewModel, LikedRecipeViewModel)
        }

        composable(NavigationEnum.uploadRecipeOneScreen.name){
            uploadRecipeOneScreen(navController,sharedViewModel,UploadRecipeViewModel)
        }

        composable(NavigationEnum.uploadRecipeTwoScreen.name){
            uploadRecipeTwoScreen(navController,sharedViewModel,UploadRecipeViewModel)
        }

        composable(NavigationEnum.searchByIngredientsScreen.name){
            searchByIngredientsScreen(navController, sharedViewModel)
        }

        composable(NavigationEnum.searchRecipesScreen.name){
            searchRecipesScreen(navController, sharedViewModel)
        }

        composable(NavigationEnum.DetailedRecipesScreen.name){
            DetailedRecipesScreen(navController, sharedViewModel)

        }


    }
}