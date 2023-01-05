package com.uc.lechef.screens.botnavbar_pages

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.Navigation.NavigationEnum
import com.uc.lechef.R
import com.uc.lechef.screens.ViewModel.sharedAllScreenViewModel
import com.uc.lechef.screens.botnavbar_pages.ui.theme.LeChefTheme

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailedRecipesScreen(navController: NavHostController = rememberNavController(),
                          sharedViewModel: sharedAllScreenViewModel,
) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val ScrollState = rememberScrollState()



    LaunchedEffect(key1 = sharedViewModel.DetailedRecipeFORCHECK.collectAsState(initial = false).value){
        if (sharedViewModel.DetailedRecipe.value != null) {
            sharedViewModel.RecipeoftheDayFORCHECK.value = true
        }
        sharedViewModel.RecipeoftheDayFORCHECK.value = false
    }



    Column(
        modifier = Modifier.verticalScroll(ScrollState)
    ) {

        TopAppBar(
            contentPadding = PaddingValues(),
            elevation = 4.dp,
            modifier = Modifier.height(100.dp)

        ) {
            Column {
                val imageData = Base64.decode(sharedViewModel.DetailedRecipe.value?.resep?.Foto, Base64.DEFAULT);

                val bitmap: Bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)

                val imageBitmap: ImageBitmap = bitmap.asImageBitmap()
                Box() {
                    Image(
                        imageBitmap,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Box(
                        modifier = Modifier
                            .background(
                                Brush.verticalGradient(
                                    colorStops = arrayOf(
                                        Pair(0.4f, Transparent),
                                        Pair(1f, White)
                                    )
                                )
                            )
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ){
                        Card(
                            elevation = 4.dp
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier.clickable {
                                    navController.popBackStack()
                                        //for some reason ga mau back
                                }
                            )
                        }
                        Card(
                            elevation = 4.dp,
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.FavoriteBorder,
                                contentDescription = null,
                                tint = Color(254,114,76),
                                modifier = Modifier.clickable {

                                }
                            )
                        }
                    }
                }


            }
        }

        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {
                //TITLE OF FOOD
                Text(text = sharedViewModel.DetailedRecipe.value?.resep?.Judul ?: "",
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle(10),
                    fontSize = 30.sp,)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,

                    ) {
                    Image(painter = painterResource(id = R.drawable.icon_star),
                        contentDescription = null,
                        modifier = Modifier.scale(3f)
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(text = sharedViewModel.DetailedRecipe.value?.resep?.Rating.toString() ?: "")
                }
            }

            //USERR NAME
            Text(text = sharedViewModel.DetailedRecipe.value?.resep?.User?.Name ?: "")

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,

                modifier = Modifier.fillMaxWidth()
            ) {
                //TIME TAKEN
                Card(modifier = Modifier
                    .padding(10.dp)
                    .height(120.dp)
                    .width(screenWidth / 6),
                    backgroundColor = Color(254, 114, 76),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,

                        ) {
                        Card(modifier = Modifier
                            .padding(8.dp)
                            .height(50.dp)
                            .fillMaxWidth(),
                            backgroundColor = White,
                            shape = CircleShape

                        ) {
                            Image(painter = painterResource(id = R.drawable.icon_black_time),
                                contentDescription = null,
                                modifier = Modifier.padding(5.dp)
                            )
                        }

                        //TIME TAKEN
                        Text(text = sharedViewModel.DetailedRecipe.value?.resep?.Timetaken ?: "")
                    }
                }

                //calories
                Card(modifier = Modifier
                    .padding(10.dp)
                    .height(120.dp)
                    .width(screenWidth / 6),
                    backgroundColor = Color(254, 114, 76),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,

                        ) {
                        Card(modifier = Modifier
                            .padding(8.dp)
                            .height(50.dp)
                            .fillMaxWidth(),
                            backgroundColor = White,
                            shape = CircleShape

                        ) {
                            Image(painter = painterResource(id = R.drawable.icon_calories),
                                contentDescription = null,
                                modifier = Modifier.padding(5.dp)
                            )
                        }

                        //cals
                        Text(text = sharedViewModel.DetailedRecipe.value?.resep?.Totalcal.toString() ?: "")
                    }
                }

                //servings
                Card(modifier = Modifier
                    .padding(10.dp)
                    .height(120.dp)
                    .width(screenWidth / 6),
                    backgroundColor = Color(254, 114, 76),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,

                        ) {
                        Card(modifier = Modifier
                            .padding(8.dp)
                            .height(50.dp)
                            .fillMaxWidth(),
                            backgroundColor = White,
                            shape = CircleShape

                        ) {
                            Image(painter = painterResource(id = R.drawable.icon_servings),
                                contentDescription = null,
                                modifier = Modifier.padding(5.dp)
                            )
                        }

                        //Portion
                        Text(text = sharedViewModel.DetailedRecipe.value?.resep?.Portionsize.toString()+" servings" ?: "",
                             overflow = TextOverflow.Ellipsis)
                    }
                }

                //good
                Card(modifier = Modifier
                    .padding(10.dp)
                    .height(120.dp)
                    .width(screenWidth / 6),
                    backgroundColor = Color(254, 114, 76),
                    shape = RoundedCornerShape(20.dp)
                ) {
                        Card(modifier = Modifier
                            .padding(8.dp)
                            .height(50.dp)
                            .fillMaxWidth(),
                            backgroundColor = White,
                            shape = CircleShape

                        ) {
                            Image(painter = painterResource(id = R.drawable.icon_thumbsup),
                                contentDescription = null,
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                }
            }

            Text(text = "Ingredients",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle(10),
                fontSize = 20.sp,)
            Column {
                for (bahan in sharedViewModel.DetailedRecipe.value?.resep?.Listbahan!!) {
                    Row{
                        Text(text = "- " + bahan.Jumlahbahan, fontWeight = FontWeight.Bold)
                        Text(text = " "+bahan.Bahan.Namabahan)
                    }
                }
            }

            Text(text = "Instructions",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle(10),
                fontSize = 20.sp,)

            var steps = ArrayList<String>()
            var start = 0
            for(i in 0..sharedViewModel.DetailedRecipe.value?.resep?.Steps.toString().length-1){
                if(sharedViewModel.DetailedRecipe.value?.resep?.Steps.toString().get(i) == ','){
                    steps.add(sharedViewModel.DetailedRecipe.value?.resep?.Steps.toString().substring(start, i))
                    start = i+2
                }
            }

            Column() {
                for (i in 0..steps.size-1){
                    Text(text = steps.get(i))
                    Log.d(i.toString(),steps.get(i))
                }
            }

            Text(text = "Pictures",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle(10),
                fontSize = 20.sp,)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,

                ) {

                //PICTURE BISA SINGLE BISA DI FOR LOOP
                Image(painter = painterResource(id = R.drawable.pic1homescreen),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .width(screenWidth / 4),

                    )

                Image(painter = painterResource(id = R.drawable.pic1homescreen),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .width(screenWidth / 4)
                    ,

                    )

                Image(painter = painterResource(id = R.drawable.pic1homescreen),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .width(screenWidth / 4),

                    )

                //KALO MAU FOR LOOP END DI SINI

            }

            Text(text = "Rate This Recipe",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle(10),
                fontSize = 20.sp,
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ){
                //1
                Image(painter = painterResource(id = R.drawable.icon_empty_star),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .scale(2f)
                )
                //2
                Image(painter = painterResource(id = R.drawable.icon_empty_star),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .scale(2f)

                )
                //3
                Image(painter = painterResource(id = R.drawable.icon_empty_star),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .scale(2f)

                )
                //4
                Image(painter = painterResource(id = R.drawable.icon_empty_star),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .scale(2f)

                )
                //5
                Image(painter = painterResource(id = R.drawable.icon_empty_star),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .scale(2f)
                )
            }
        }


    }
}