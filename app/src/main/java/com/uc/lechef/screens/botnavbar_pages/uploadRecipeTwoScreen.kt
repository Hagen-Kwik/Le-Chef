package com.uc.lechef.screens.botnavbar_pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.Navigation.NavigationEnum
import com.uc.lechef.screens.botnavbar_pages.ui.theme.LeChefTheme


@Preview(showBackground = true)
@Composable
fun uploadRecipeTwoScreen(navController: NavHostController = rememberNavController()) {


    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Name")
        TextField(value = "", onValueChange = {},
            placeholder = { Text("Name of recipe...") }
        )

        Text(text = "Description of Food")
        TextField(value = "", onValueChange = {},
            placeholder = { Text("The food's Description of your recipe...") }
        )

        Text(text = "Ingredients")
        TextField(value = "", onValueChange = {},
            placeholder = { Text("Ingredients of your recipe...") }
        )

        Text(text = "Time Needed")
//        time
        TextField(value = "", onValueChange = {},
            placeholder = { Text("Ingredients of your recipe...") }
        )

        Text(text = "Instructions")
        TextField(value = "", onValueChange = {},
            placeholder = { Text("Cooking Instructions") }
        )

        Button(onClick = {
            navController.popBackStack(route = NavigationEnum.botnavbar.name, inclusive = false)
        }) {
            Text("Finish")
        }


    }
}

