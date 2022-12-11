package com.uc.lechef.graphs

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.uc.lechef.repository.userRepository
import com.uc.lechef.screens.LoginPage
import com.uc.lechef.screens.SignUpPage
import com.uc.lechef.screens.ViewModel.signUpScreenViewModel
import com.uc.lechef.retrofit.EndPointApi

private lateinit var viewModel: signUpScreenViewModel

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {



    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginPage(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.HOME)
                          },
                onSignUpClick = {
                navController.navigate(AuthScreen.SignUp.route)
                }
            )
        }
        composable(route = AuthScreen.SignUp.route) {
            SignUpPage(
                onAlreadyHaveAccountClick = { navController.navigate(AuthScreen.Login.route) },
                onSignedUpClick = { navController.navigate(AuthScreen.Login.route) },
                viewModel = viewModel
            )
        }
    }
}

fun user(me: String, me1: String, me2: String){}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
//    object Forgot : AuthScreen(route = "FORGOT")
}