package com.uc.lechef.screens.botnavbar_pages

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.Navigation.NavigationEnum
import com.uc.lechef.screens.ViewModel.sharedAllScreenViewModel
import com.uc.lechef.screens.botnavbar_pages.ui.theme.LeChefTheme


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun searchRecipesScreen(navController: NavHostController = rememberNavController(),
                        sharedViewModel: sharedAllScreenViewModel,) {
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Column(modifier = Modifier.fillMaxSize()) {
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
//                        navController.popBackStack()
                            navController.navigate(NavigationEnum.DetailedRecipesScreen.name)

                        }
                    )

                    Text(text = "Results")

                    Spacer(modifier = Modifier.width(0.dp))
                }
            }
//    give items here

        }

        var i = 0
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
                            modifier = Modifier
                                .alpha(0.8f)
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
    }

}