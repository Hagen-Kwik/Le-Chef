package com.uc.lechef.screens.botnavbar_pages

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.Models.Bahan
import com.uc.lechef.Models.bahanAll
import com.uc.lechef.Navigation.NavigationEnum
import com.uc.lechef.R
import com.uc.lechef.helper.StoreUserCookie
import com.uc.lechef.screens.ViewModel.SearchByIngredientsScreenViewModel
import com.uc.lechef.screens.ViewModel.sharedAllScreenViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun searchByIngredientsScreen(
    navController: NavHostController = rememberNavController(),
    sharedViewModel: sharedAllScreenViewModel,
    SearchByIngredientViewModel: SearchByIngredientsScreenViewModel,
) {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val context = LocalContext.current
    val COOKIE = StoreUserCookie(context)

    var searchbar = remember { mutableStateOf("") }


    LaunchedEffect(key1 = SearchByIngredientViewModel.changedToSearchRecipe.collectAsState(false).value) {
        if (SearchByIngredientViewModel.changedToSearchRecipe.value) {
            SearchByIngredientViewModel.searchRecipe.value?.let { sharedViewModel.AddSearchRecipe(it) }
            SearchByIngredientViewModel.changedToSearchRecipe.value = false
            navController.navigate(NavigationEnum.searchRecipesScreen.name)
        }
    }

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
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.padding(20.dp)

            ) {
            var bahans: List<Bahan>
                items(sharedViewModel.IngredientsTrending.value?.Bahan!!) {     bahan ->
                    Card() {
                        val imageData =
                            Base64.decode(bahan.Foto,
                                Base64.DEFAULT);

                        val bitmap: Bitmap =
                            BitmapFactory.decodeByteArray(imageData, 0, imageData.size)

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
                                    .clickable {
                                        //add item ke arraay list disini
                                        SearchByIngredientViewModel.addBahanToArray(sharedViewModel.IngredientsTrending.value?.Bahan!!.get(
                                            i).Namabahan)
                                    }
                            ) {
                                Text(text = bahan.Namabahan)
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
//            items(sharedViewModel.IngredientsTrending.value?.Bahan!!) {
//                //cardview here
//
//
//            }

            Box(contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {
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
//                        navController.navigate(NavigationEnum.searchRecipesScreen.name)
                                SearchByIngredientViewModel.searchRecipe(COOKIE.getCookie)
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
}

//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun PhotoGrid(photos: List<Bahan>) {
//    LazyVerticalGrid(
//        columns = GridCells.Adaptive(minSize = 128.dp)
//    ) {
//        var test = photos
//        var test2: List<Bahan> = test
//        items(test) { photo ->
//            PhotoItem(photo)
//        }
//    }
//}





