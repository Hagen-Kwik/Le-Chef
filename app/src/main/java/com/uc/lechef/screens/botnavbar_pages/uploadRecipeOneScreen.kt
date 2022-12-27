package com.uc.lechef.screens.botnavbar_pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.Navigation.NavigationEnum
import com.uc.lechef.screens.botnavbar_pages.ui.theme.LeChefTheme

@Preview(showBackground = true)
@Composable
fun uploadRecipeOneScreen(navController: NavHostController = rememberNavController()) {


    Column(modifier = Modifier.fillMaxSize()
        .padding(10.dp)
    ) {
        
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
        ){
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )

            Text(text = "Upload Your Recipe")
            Text(text = "0")

        }
        
        Text(text = "Name")
        TextField(value = "", onValueChange = {},
            placeholder = { Text("Name of recipe...") },
            modifier = Modifier.fillMaxWidth()
                .padding(0.dp, 5.dp)
        )

        Text(text = "Description of Food")
        TextField(value = "", onValueChange = {},
            placeholder = { Text("The food's Description of your recipe...") },
            modifier = Modifier.fillMaxWidth()
            .padding(0.dp, 5.dp)

        )

        Text(text = "Ingredients")
        TextField(value = "", onValueChange = {},
            placeholder = { Text("Ingredients of your recipe...") },
            modifier = Modifier.fillMaxWidth()
                .padding(0.dp, 5.dp)

        )

        Text(text = "Time Needed")
//        time
        TextField(value = "", onValueChange = {},
            placeholder = { Text("Ingredients of your recipe...") },
            modifier = Modifier.fillMaxWidth()
                .padding(0.dp, 5.dp)

        )

        Text(text = "Instructions")
        TextField(value = "", onValueChange = {},
            placeholder = { Text("Cooking Instructions") },
            modifier = Modifier.fillMaxWidth()
                .padding(0.dp, 5.dp)

        )
        
        Button(onClick = {
            navController.navigate(NavigationEnum.uploadRecipeTwoScreen.name)
        },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Next")
        }


    }
}

