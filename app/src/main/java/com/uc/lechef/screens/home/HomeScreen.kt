package com.uc.lechef.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.graphs.HomeNavGraph
import com.uc.lechef.Navigation.NavigationItem



@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()){
    Scaffold(scaffoldState = rememberScaffoldState(),
        backgroundColor = MaterialTheme.colors.background,
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ },
                elevation = FloatingActionButtonDefaults.elevation(2.dp, 3.dp)
            ) {
//                Icon(
//                    painter = painterResource(id = ),
//                    contentDescription = "fab",
//                )
                Text(text = "+")
            }
        },
        bottomBar = {
            BottomNav()
        }
    ){
        HomeNavGraph(navController = navController)
    }
}

@Composable
fun NavItem(item: NavigationItem, isSelected: Boolean, onCLick:()-> Unit) {
    val iconId = if(isSelected) item.Selectedicon else item.unSelectedIcon
    val iconAlpha = if(isSelected) 1f else 0.5f
    IconButton(onClick = {onCLick}){
        Icon(painter = painterResource(id = iconId),
            contentDescription = item.title,
            tint = MaterialTheme.colors.onBackground.copy(alpha = iconAlpha))
    }
}

@Composable
fun BottomNav() {
    var currentSelectedScreenId by remember { mutableStateOf(0) }

    BottomAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        cutoutShape = CircleShape,
        contentPadding = PaddingValues(horizontal = 50.dp),
        elevation = 2.dp
    ) {
        NavigationItem.Home.let { Home ->
            NavItem(item = Home, isSelected = Home.id == currentSelectedScreenId) {
                currentSelectedScreenId = Home.id
            }
        }

        NavigationItem.MyRecipes.let { MyRecipes ->
            NavItem(item = MyRecipes, isSelected = MyRecipes.id == currentSelectedScreenId) {
                currentSelectedScreenId = MyRecipes.id
            }
        }

        Spacer(Modifier.weight(1f))

        NavigationItem.LikedRecipes.let { LikedRecipes ->
            NavItem(item = LikedRecipes, isSelected = LikedRecipes.id == currentSelectedScreenId) {
                currentSelectedScreenId = LikedRecipes.id
            }
        }

        NavigationItem.Profile.let { Profile ->
            NavItem(item = Profile, isSelected = Profile.id == currentSelectedScreenId) {
                currentSelectedScreenId = Profile.id
            }
        }

    }
}



