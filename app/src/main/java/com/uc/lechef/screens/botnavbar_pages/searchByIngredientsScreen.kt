package com.uc.lechef.screens.botnavbar_pages

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.Navigation.NavigationEnum
import com.uc.lechef.R
import com.uc.lechef.screens.ViewModel.sharedAllScreenViewModel
import com.uc.lechef.screens.botnavbar_pages.ui.theme.LeChefTheme

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun searchByIngredientsScreen(navController: NavHostController = rememberNavController(),
                              sharedViewModel: sharedAllScreenViewModel,
) {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val ScrollState = rememberScrollState()
    var searchbar = remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()) {
        LazyColumn() {
            stickyHeader {

                Row(horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )

                    Text(text = "Find Recipes")

                    Spacer(modifier = Modifier.width(0.dp))
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp, 0.dp, 20.dp, 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {
                    TextField(
                        modifier = Modifier
                            .height(50.dp),
                        value = searchbar.value,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Gray,
                            cursorColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(30.dp),
                        onValueChange = {
                            searchbar.value = it
                        },
                        trailingIcon = {
                            if (searchbar.value.isNotEmpty()) {
                                Icon(
                                    imageVector = Icons.Outlined.Close,
                                    contentDescription = null,
                                    modifier = Modifier.clickable {
                                        searchbar.value = ""
                                    }
                                )
                            }
                        },
                        placeholder = { Text("Search Here...") }
                    )

                    Icon(imageVector = Icons.Outlined.Search, contentDescription = null,
                        modifier = Modifier
                            .scale(1.5f)
                            .clickable {
//                searchbar.value =
                            }
                    )
                }
            }
//    give items here
        }
        var i = 0
        Column() {
            
        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            
            modifier = Modifier.padding(20.dp)
        ) {
            items(sharedViewModel.IngredientsTrending.value?.Bahan!!.size) {
                //cardview here

                Card() {
                    val imageData = Base64.decode(sharedViewModel.IngredientsTrending.value?.Bahan!!.get(i).Foto, Base64.DEFAULT);

                    val bitmap: Bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)

                    val imageBitmap: ImageBitmap = bitmap.asImageBitmap()

                    Image(
                        imageBitmap,
                        contentDescription = "ingridient",
                        Modifier
                            .height(screenWidth / 3)
                            .width(screenWidth / 3),
                        contentScale = ContentScale.Crop,
                    )
                    Row(
                            verticalAlignment = Alignment.Bottom,
                            modifier = Modifier
                                .height(screenWidth / 3)
                                .width(screenWidth / 3)
                     ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.alpha(0.8f)
                            .background(Color.Gray)
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp)
                        ) {
                            Text(text = sharedViewModel.IngredientsTrending.value?.Bahan!!.get(i).Namabahan)
                            i++
                            Icon(
                                imageVector = Icons.Rounded.Add,
                                contentDescription = null,
                                tint = Color.Black,
                            )
                        }
                    }
                }
            }
        }

        Box(contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ){
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                // see items
                Column(
                    modifier = Modifier
                        .clickable {
//                searchbar.value =
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,


                    ) {
                    Icon(imageVector = Icons.Outlined.List, contentDescription = null,
                        modifier = Modifier
                            .scale(1.5f)
                    )

                    Text(text = "See Items")
                }

                // items in pot
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,

                    ) {
                    Image(painter = painterResource(id = R.drawable.pot_empty),
                        contentDescription = null,
                        modifier = Modifier
                            .height(80.dp)
                    )

                    Text(text = "Items in Pot: 0")
                }

                // search
                Column(
                    modifier = Modifier
                        .clickable {
//                searchbar.value =
                        navController.navigate(NavigationEnum.searchRecipesScreen.name)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,

                    ) {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = null,
                        modifier = Modifier
                            .scale(1.5f)
                    )

                    Text(text = "search")
                }
            }
        }
    }
}








