package com.uc.lechef.screens.botnavbar_pages

import android.annotation.SuppressLint
import android.graphics.Color.YELLOW
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.Navigation.NavigationEnum
import com.uc.lechef.R
import com.uc.lechef.helper.StoreUserCookie
import com.uc.lechef.screens.ViewModel.HomeScreenViewModel
import com.uc.lechef.screens.ViewModel.sharedAllScreenViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController(),
               sharedViewModel: sharedAllScreenViewModel,
               HomeScreenViewModel: HomeScreenViewModel

){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    LaunchedEffect(key1 = sharedViewModel.RecipeoftheDayFORCHECK.collectAsState(initial = false).value){
        if (sharedViewModel.RecipeOfTheDay.value != null) {
            sharedViewModel.RecipeoftheDayFORCHECK.value = true
        }
    }

    Log.d("here", StoreUserCookie.cookie_User.toString())

    LaunchedEffect(key1 = sharedViewModel.IngredientsTrendingFORCHECK.collectAsState(initial = false).value){
        if (sharedViewModel.IngredientsTrending.value != null) {
            sharedViewModel.IngredientsTrendingFORCHECK.value = true
        }
    }

    LaunchedEffect(key1 = sharedViewModel.RecipeTrendingFORCHECK.collectAsState(initial = false).value){
        if (sharedViewModel.RecipeTrending.value != null) {
            sharedViewModel.RecipeTrendingFORCHECK.value = true
        }
    }

    //chech what this is later
    val ScrollState = rememberScrollState()
    val ScrollStateTrendingINGRE = rememberScrollState()


    Column(
        modifier = Modifier
            .verticalScroll(ScrollState)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .background(color = Color(YELLOW))
                    .fillMaxSize()
            ) {
                //pic later changfe
                Image(
                    painter = painterResource(id = R.drawable.pic1homescreen),
                    contentDescription = "homePicture",
                    Modifier
                        .height(screenHeight / 4)
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = Modifier
                        .padding(20.dp,((screenHeight / 4)/5),0.dp,0.dp)
                ) {
                    Text(text = "Recipe of the day",
                        fontSize = 18.sp,

                        color = Color.White
                    )
                    //name of food later change
                    Text(text = sharedViewModel.RecipeOfTheDay.value?.resep?.Judul ?: "",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle(10),
                        fontSize = 30.sp,
                        color = Color.White
                    )
                    Text(text = "See Details",
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                        fontSize = 14.sp,
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline
                        ),
                        modifier = Modifier.
                        clickable{
                            //give navigation to search?
                        }
                    )
                }
            }
        }
        //Trending Ingridients
        Column(
            modifier = Modifier
                .padding(20.dp, 20.dp, 20.dp, 0.dp)
                .fillMaxWidth(),
            )
        {
            Text(
                text = "Trending Ingridients",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle(10),
//                color = Color.White
            )

            //for loop here

                    Row(
                        modifier = Modifier
                            .horizontalScroll(ScrollStateTrendingINGRE)
                            .fillMaxSize()

                    ) {
                        var i = 0
                        for (item in sharedViewModel.IngredientsTrending.value?.Bahan!!) {
                            if (i < 5) {
                        Card(
                            modifier = Modifier.padding(5.dp),
                            elevation = 3.dp
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable {

                                    },
                                contentAlignment = Alignment.BottomCenter

                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ingridient1),
                                    contentDescription = "ingridient",
                                    Modifier
                                        .width(screenWidth / 4)
                                        .height(screenHeight / 8),
                                    contentScale = ContentScale.Crop,
                                )
                                Text(text = item.Namabahan,
                                    modifier = Modifier.padding(5.dp)
                                )
                            }
                        }
                    }
                i++
                }
            }
        }
    //Trending recipes
        Column(
            modifier = Modifier
                .padding(20.dp, 20.dp, 20.dp, 0.dp)
                .fillMaxWidth(),
        )
        {
            Text(text = "Trending Recipes",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle(10),
//                color = Color.White
            )

            //for loopnya disini
            Column {

                var i = 0
                for (resep in sharedViewModel.RecipeTrending.value?.Resep!!) {
                    if (i < 3) {
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxSize(),
                            elevation = 3.dp
                        ) {
                            Row(
                            ) {
                                //change pic of recipe
                                Image(
                                    painter = painterResource(id = R.drawable.ingridient1),
                                    contentDescription = "ingridient",
                                    Modifier
                                        .width(screenWidth / 3)
                                        .height(screenHeight / 7),
                                    contentScale = ContentScale.Crop,
                                )
                                Column(
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    //judul
                                    Column {
                                        Text(text = resep.Judul)
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceEvenly,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            //icon time
                                            Icon(
                                                painter = painterResource(id = R.drawable.icon_time),
                                                contentDescription = null,
                                                tint = Color(254,114,76),
                                                modifier = Modifier
                                                    .scale(3f)
                                                )
                                            //text time taken
                                            Text(text = resep.Timetaken)
                                        }
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceEvenly,

                                            ) {
                                            //icon rating
                                            Icon(
                                                painter = painterResource(id = R.drawable.icon_star),
                                                contentDescription = null,
                                                tint = Color(254,114,76),
                                                modifier = Modifier
                                                    .scale(3f)
                                            )
                                            //text rating
                                            Text(text = resep.Rating.toString())
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(15.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        //see details text
                                        Text(text = "See Details",
                                            fontWeight = FontWeight.Light,
                                            style = TextStyle(
                                                textDecoration = TextDecoration.Underline
                                            ),
                                            modifier = Modifier.clickable {
                                                //give navigation to recipe details
                                            }
                                        )
                                        //icon for heart
                                        Icon(painter = painterResource(id = R.drawable.icon_unliked),
                                            tint = Color(254,114,76),
                                            contentDescription = "Like Button",
                                            modifier = Modifier
                                                .scale(1.5f)
                                                .clickable {
                                                    //add to favourites
                                                }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

