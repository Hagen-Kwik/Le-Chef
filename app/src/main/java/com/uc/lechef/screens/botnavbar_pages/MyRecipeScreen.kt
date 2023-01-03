package com.uc.lechef.screens.botnavbar_pages

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.Navigation.NavigationEnum
import com.uc.lechef.R
import com.uc.lechef.screens.ViewModel.sharedAllScreenViewModel

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MyRecipesScreen(navController: NavHostController = rememberNavController(),
                    sharedViewModel: sharedAllScreenViewModel,) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val ScrollState = rememberScrollState()
    var searchbar = remember { mutableStateOf("") }

    LazyColumn() {
        stickyHeader {

            Text(text = "My tes page",
                modifier = Modifier.padding(20.dp),
                color = Color(249, 162, 46)
            )

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

            //    give items here
            Column {
                for (item in sharedViewModel.ResepbyUser.value?.resep!!) {
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxSize(),
                        elevation = 3.dp
                    ) {
                        Row(
                        ) {
                            //change pic of recipe
                            val imageData = Base64.decode(item.Foto, Base64.DEFAULT);

                            val bitmap: Bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)

                            val imageBitmap: ImageBitmap = bitmap.asImageBitmap()
                            Image(
                                imageBitmap,
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
                                    Text(text = item.Judul)
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
                                        Text(text = item.Timetaken)
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
                                        Text(text = item.Rating.toString())
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

            Box( modifier = Modifier.padding(0.dp,0.dp,20.dp,80.dp)
                .fillMaxSize()) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(NavigationEnum.uploadRecipeOneScreen.name)
                    },
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(2.dp, 3.dp),
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            }
        }
    }
}
