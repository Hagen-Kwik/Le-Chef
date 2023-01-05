package com.uc.lechef.screens.botnavbar_pages

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.uc.lechef.Navigation.NavigationEnum
import com.uc.lechef.R
import com.uc.lechef.helper.StoreUserCookie
import com.uc.lechef.screens.ViewModel.SearchRecipeScreenViewModel
import com.uc.lechef.screens.ViewModel.sharedAllScreenViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun searchRecipesScreen(
    navController: NavHostController = rememberNavController(),
    sharedViewModel: sharedAllScreenViewModel,
    SearchRecipeViewModel: SearchRecipeScreenViewModel,) {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val context = LocalContext.current
    val COOKIE = StoreUserCookie(context)

    LaunchedEffect(key1 = SearchRecipeViewModel.changedToDetailed.collectAsState().value){
        if (SearchRecipeViewModel.changedToDetailed.value) {
            SearchRecipeViewModel.resepSpecific.value?.let { sharedViewModel.addDetailedRecipe(it) }
            SearchRecipeViewModel.changedToDetailed.value = false
            navController.navigate(NavigationEnum.DetailedRecipesScreen.name)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn() {
            stickyHeader {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
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
                    Text(text = "Results")
                    Spacer(modifier = Modifier.width(0.dp))
                }
            }
        }

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.padding(20.dp).fillMaxHeight()

        ) {
            if (sharedViewModel.SearchRecipe.value.toString() != "null") {
                items(sharedViewModel.SearchRecipe.value?.resep!!) { bahan ->
                    Card(modifier = Modifier.clickable {
                        SearchRecipeViewModel.moveToDetailed(bahan.ID, COOKIE.getCookie)
                    }, elevation = 5.dp
                    ) {
                        val imageData =
                            Base64.decode(
                                bahan.Foto,
                                Base64.DEFAULT
                            );

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
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier
                                .height(screenWidth / 3)
                                .width(screenWidth / 3)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_star),
                                    contentDescription = null,
                                    tint = Color(254, 114, 76),
                                    modifier = Modifier.scale(2f)
                                )
                                Text(text = bahan.Timetaken)
                            }
                        }
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
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icon_time),
                                        contentDescription = null,
                                        tint = Color.Black,
                                        modifier = Modifier.scale(2f)

                                    )
                                    Text(text = bahan.Timetaken)
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icon_calories),
                                        contentDescription = null,
                                        tint = Color.Black,
                                        modifier = Modifier.scale(2f)

                                    )
                                    Text(text = bahan.Totalcal.toString() + "cal")
                                }
                            }
                            Text(text = bahan.Judul,
                                color = Color.Black
                            )
                        }
                    }
                }
            } else {
                items(sharedViewModel.RecipeTrending.value?.Resep!!) { bahan ->
                    Card(modifier = Modifier.clickable {
                        SearchRecipeViewModel.moveToDetailed(bahan.ID, COOKIE.getCookie)
                    }, elevation = 5.dp
                    ) {
                        val imageData =
                            Base64.decode(
                                bahan.Foto,
                                Base64.DEFAULT
                            );

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
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier
                                .height(screenWidth / 3)
                                .width(screenWidth / 3)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_star),
                                    contentDescription = null,
                                    tint = Color(254, 114, 76),
                                )
                                Text(text = bahan.Timetaken)
                            }
                        }
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
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icon_time),
                                        contentDescription = null,
                                        tint = Color.Black,
                                    )
                                    Text(text = bahan.Timetaken)
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icon_calories),
                                        contentDescription = null,
                                        tint = Color.Black,
                                    )
                                    Text(text = bahan.Totalcal.toString() + "cal")
                                }
                            }
                            Text(text = bahan.Judul,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}