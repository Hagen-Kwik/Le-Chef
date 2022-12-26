package com.uc.lechef.screens.botnavbar_pages

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun MyRecipesScreen(navController: NavHostController = rememberNavController()) {
    Text(text = "My Recipe Page!")
    botnavbar(navController)

}

