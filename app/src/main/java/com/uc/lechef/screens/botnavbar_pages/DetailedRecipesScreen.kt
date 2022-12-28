package com.uc.lechef.screens.botnavbar_pages

import android.media.Image
import android.os.Bundle
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.Navigation.NavigationEnum
import com.uc.lechef.R
import com.uc.lechef.screens.botnavbar_pages.ui.theme.LeChefTheme

@Preview(showBackground = true)
@Composable
fun DetailedRecipesScreen(navController: NavHostController = rememberNavController()) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val ScrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(ScrollState)
    ) {

        TopAppBar(
            contentPadding = PaddingValues(),
            elevation = 4.dp,
            modifier = Modifier.height(100.dp)

        ) {
            Column {

                Box() {
                    Image(
                        painter = painterResource(id = R.drawable.pic1homescreen),
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
//                        navController.popBackStack()
                                    navController.navigate(NavigationEnum.DetailedRecipesScreen.name)
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
                Text(text = "Green Baked Eggs")

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(5.dp)
                ) {
                    Image(painter = painterResource(id = R.drawable.icon_star),
                        contentDescription = null,
                        modifier = Modifier.scale(3f)
                    )
                    Spacer(modifier = Modifier.weight(5f))
                    Text(text = "5.0")
                }
            }

            //USERR NAME
            Text(text = "Gordell Ramsey")

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,

                modifier = Modifier.fillMaxWidth()
            ) {
                //TIME TAKEN
                Card(modifier = Modifier
                    .padding(10.dp)
                    .height(100.dp)
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
                        Text(text = "10 min")
                    }
                }

                //calories
                Card(modifier = Modifier
                    .padding(10.dp)
                    .height(100.dp)
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
                        Text(text = "452 Cal")
                    }
                }

                //servings
                Card(modifier = Modifier
                    .padding(10.dp)
                    .height(100.dp)
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
                        Text(text = "2 portion",
                             overflow = TextOverflow.Ellipsis)
                    }
                }

                //good
                Card(modifier = Modifier
                    .padding(10.dp)
                    .height(100.dp)
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

            Text(text = "Ingredients")
            Row{
                Text(text = "- 320g",fontWeight = FontWeight.Bold)
                Text(text = " frozen Peas")
            }

            Text(text = "Instructions")
            Text(text = "STEP ONE I CAN DO ALL THINGS")

            Text(text = "Pictures")
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,

                ) {
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
            }

            Text(text = "Rate This Recipe",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ){
                Image(painter = painterResource(id = R.drawable.icon_empty_star),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .scale(3f)
                )
                Image(painter = painterResource(id = R.drawable.icon_empty_star),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .scale(3f)

                )
                Image(painter = painterResource(id = R.drawable.icon_empty_star),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .scale(3f)

                )
                Image(painter = painterResource(id = R.drawable.icon_empty_star),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .scale(3f)

                )
                Image(painter = painterResource(id = R.drawable.icon_empty_star),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(5.dp)
                        .scale(3f)

                )
            }
        }


    }
}