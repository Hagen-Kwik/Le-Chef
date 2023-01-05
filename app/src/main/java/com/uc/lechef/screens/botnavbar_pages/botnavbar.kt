package com.uc.lechef.screens.botnavbar_pages

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.uc.lechef.Navigation.NavigationEnum
import com.uc.lechef.R
import com.uc.lechef.screens.ViewModel.HomeScreenViewModel
import com.uc.lechef.screens.ViewModel.LikedRecipeScreenViewModel
import com.uc.lechef.screens.ViewModel.MyrecipeScreenViewModel
import com.uc.lechef.screens.ViewModel.sharedAllScreenViewModel

@Composable
fun botnavbar(navController: NavHostController,
              sharedViewModel: sharedAllScreenViewModel,
              HomeScreenViewModel: HomeScreenViewModel,
              MyrecipeViewModel: MyrecipeScreenViewModel,
              LikedRecipeViewModel: LikedRecipeScreenViewModel


) {
    Scaffold(
        scaffoldState = rememberScaffoldState(),
    floatingActionButtonPosition = FabPosition.Center,
    isFloatingActionButtonDocked = true,
    floatingActionButton = {
        FloatingActionButton(onClick = { /*TODO*/ },
            elevation = FloatingActionButtonDefaults.elevation(2.dp, 3.dp),
            backgroundColor = Color(249,162,46)
        ) {
//                Icon(
//                    painter = painterResource(id = ),
//                    contentDescription = "fab",
//                )
            Image(painter = painterResource(id = R.drawable.logo_white),
                contentDescription = "Logo",

                modifier = Modifier
                    .clickable {
                        navController.navigate(NavigationEnum.searchByIngredientsScreen.name)
                    }
                    .height(30.dp)
            )
        }
    },
        content = {
                padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                if (currentSelectedScreenId.value == 0) HomeScreen(navController, sharedViewModel, HomeScreenViewModel)
                else if (currentSelectedScreenId.value == 1) MyRecipesScreen(navController,sharedViewModel, MyrecipeViewModel)
                else if (currentSelectedScreenId.value == 2) LikedRecipesScreen(navController, sharedViewModel, LikedRecipeViewModel)
                else if (currentSelectedScreenId.value == 3) ProfileScreen(navController, sharedViewModel)
            }
        },

    bottomBar = {
        BottomNav()
    }
    )
}




var currentSelectedScreenId = mutableStateOf(0)


@Composable
fun BottomNav() {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        cutoutShape = CircleShape,
        contentPadding = PaddingValues(horizontal = 25.dp),
        elevation = 2.dp
    ) {

        BottomNavigationItem(selected = true, onClick = {
            currentSelectedScreenId.value = NavigationItem.Home.id
        },
            icon = {
                Icon(painter = painterResource(id = NavigationItem.Home.Selectedicon),
                    contentDescription = NavigationItem.Home.title,
                    tint = MaterialTheme.colors.onBackground.copy(alpha = if (currentSelectedScreenId.value == NavigationItem.Home.id) 1f else 0.5f),
                    modifier = Modifier.scale(3f)
                )
            }
        )

        BottomNavigationItem(selected = true, onClick = {
            currentSelectedScreenId.value = NavigationItem.MyRecipes.id
        },
            icon = {
                Icon(painter = painterResource(id = NavigationItem.MyRecipes.Selectedicon),
                    contentDescription = NavigationItem.MyRecipes.title,
                    tint = MaterialTheme.colors.onBackground.copy(alpha = if (currentSelectedScreenId.value == NavigationItem.MyRecipes.id) 1f else 0.5f),
                    modifier = Modifier.scale(3f)
                )
            }
        )



        Spacer(Modifier.weight(0.5f))

        BottomNavigationItem(selected = true, onClick = {
            currentSelectedScreenId.value = NavigationItem.LikedRecipes.id

        },
            icon = {
                Icon(painter = painterResource(id = NavigationItem.LikedRecipes.Selectedicon),
                    contentDescription = NavigationItem.LikedRecipes.title,
                    tint = MaterialTheme.colors.onBackground.copy(alpha = if (currentSelectedScreenId.value == NavigationItem.LikedRecipes.id) 1f else 0.5f),
                    modifier = Modifier.scale(3f)
                )
            }
        )

        BottomNavigationItem(selected = true, onClick = {
            currentSelectedScreenId.value = NavigationItem.Profile.id
        },
            icon = {
                Icon(painter = painterResource(id = NavigationItem.Profile.Selectedicon),
                    contentDescription = NavigationItem.Profile.title,
                    tint = MaterialTheme.colors.onBackground.copy(alpha = if (currentSelectedScreenId.value == NavigationItem.Profile.id) 1f else 0.5f),
                    modifier = Modifier.scale(3f)
                )
            }
        )

    }
}

sealed class NavigationItem(val id: Int, var title: String, @DrawableRes var Selectedicon:Int, @DrawableRes var unSelectedIcon:Int){
    object Home: NavigationItem(0, "home", R.drawable.botnavbarhome, R.drawable.botnavbarhome)
    object MyRecipes: NavigationItem(1, "myrecipes", R.drawable.botnavbarmyrecipes, R.drawable.botnavbarmyrecipes)
    object LikedRecipes: NavigationItem(2, "likedrecipes", R.drawable.botnavbarlikedrecipe, R.drawable.botnavbarlikedrecipe)
    object Profile: NavigationItem(3, "profile", R.drawable.botnavbarprofile, R.drawable.botnavbarprofile)
}



