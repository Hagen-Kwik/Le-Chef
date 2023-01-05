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
import androidx.compose.material.icons.rounded.Close
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
import androidx.compose.ui.text.font.FontWeight
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
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
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
        if (SearchByIngredientViewModel.changedToSearchRecipe.value == "searched") {
            SearchByIngredientViewModel.searchRecipe.value?.let { sharedViewModel.AddSearchRecipe(it) }
            SearchByIngredientViewModel.changedToSearchRecipe.value = "notsearched"
            navController.navigate(NavigationEnum.searchRecipesScreen.name)
        } else if (SearchByIngredientViewModel.changedToSearchRecipe.value == "nodata") {
            SearchByIngredientViewModel.changedToSearchRecipe.value = "notsearched"
            navController.navigate(NavigationEnum.searchRecipesScreen.name)
        }

    }




    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()


    var collapseRemebmer = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = collapseRemebmer.value) {
        bottomSheetScaffoldState.bottomSheetState.collapse()
        collapseRemebmer.value = false
    }

    LaunchedEffect(key1 = SearchByIngredientViewModel.nambah.collectAsState(false).value) {
        SearchByIngredientViewModel.nambah.value = false
    }

    LaunchedEffect(key1 = SearchByIngredientViewModel.delete.collectAsState(false).value) {
        SearchByIngredientViewModel.delete.value = false
        bottomSheetScaffoldState.bottomSheetState.expand()
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(topEnd = 40.dp, topStart = 40.dp),
        sheetContent = {
            //Ui for bottom sheet
            val ScrollState = rememberScrollState()

            Row(
                content = {
//                    Spacer(modifier = Modifier.padding(30.dp))
                    Column {
                            Row(
                                    modifier = Modifier
                                       .fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceBetween
                           ) {
                           Text(
                              text = "Items in Pot",
                              fontWeight = FontWeight.Bold,
                           )
                           Icon(
                              imageVector = Icons.Rounded.Close,
                              contentDescription = null,
                              tint = Color.Black,
                              modifier = Modifier.clickable {
                                  collapseRemebmer.value = true
                              }
                           )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (SearchByIngredientViewModel.bahan_bahanArray.isEmpty()) {
                                Text(text = "Nothing Yet")
                            } else {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                ) {
                                    for ((e, items) in SearchByIngredientViewModel.bahan_bahanArray.withIndex()) {
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(10.dp),
                                        ) {
                                            Text(text = (e + 1).toString() + ". " + items)
                                            Text(text = "Delete",
                                                color = Color.Red,
                                                modifier = Modifier.clickable {
                                                    SearchByIngredientViewModel.deleteBahan(e)
                                                }
                                            )

                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .padding(16.dp)
                    .verticalScroll(ScrollState),

                )
        },
        sheetPeekHeight = 0.dp,
    ) {


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

//                Row(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(20.dp, 0.dp, 20.dp, 10.dp),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween
//
//                ) {
//                    TextField(
//                        modifier = Modifier
//                            .height(50.dp),
//                        value = searchbar.value,
//                        colors = TextFieldDefaults.textFieldColors(
//                            backgroundColor = Color.Gray,
//                            cursorColor = Color.Black,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent
//                        ),
//                        shape = RoundedCornerShape(30.dp),
//                        onValueChange = {
//                            searchbar.value = it
//                        },
//                        trailingIcon = {
//                            if (searchbar.value.isNotEmpty()) {
//                                Icon(
//                                    imageVector = Icons.Outlined.Close,
//                                    contentDescription = null,
//                                    modifier = Modifier.clickable {
//                                        searchbar.value = ""
//                                    }
//                                )
//                            }
//                        },
//                        placeholder = { Text("Search Here...") }
//                    )
//
//                    Icon(imageVector = Icons.Outlined.Search, contentDescription = null,
//                        modifier = Modifier
//                            .scale(1.5f)
//                            .clickable {
////                searchbar.value =
//                            }
//                    )
//                }
                }
//    give items here
            }

            Column() {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier
                        .padding(20.dp)
                        .height(screenHeight * 7 / 10)

                ) {
                    items(sharedViewModel.IngredientsTrending.value?.Bahan!!) { bahan ->
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
                                    modifier = Modifier
                                        .alpha(0.8f)
                                        .background(Color.Gray)
                                        .fillMaxWidth()
                                        .padding(start = 10.dp, end = 10.dp)
                                        .clickable {
                                            //add item ke arraay list disini
                                            SearchByIngredientViewModel.addBahanToArray(bahan.Namabahan)
                                        }
                                ) {
                                    Text(text = bahan.Namabahan)
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
                                    coroutineScope.launch {
                                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                            bottomSheetScaffoldState.bottomSheetState.expand()
                                        } else {
                                            bottomSheetScaffoldState.bottomSheetState.collapse()
                                        }
                                    }
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
                            if(SearchByIngredientViewModel.bahan_bahanArray.isEmpty()){
                            Image(painter = painterResource(id = R.drawable.pot_empty),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(80.dp)
                            )
                            Text(text = "Items in Pot: 0")
                            }
                            else {
                                Image(painter = painterResource(id = R.drawable.pot_full),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(80.dp)
                                )
                                Text(text = "Items in Pot: "+SearchByIngredientViewModel.bahan_bahanArray.size.toString())
                            }
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





