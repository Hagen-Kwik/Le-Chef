package com.uc.lechef.screens.botnavbar_pages

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun LikedRecipesScreen(navController: NavHostController = rememberNavController()) {
    val ScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(ScrollState)
    )
    {
        Text(text = "Liked Recipre Scrern!")
    }
}
