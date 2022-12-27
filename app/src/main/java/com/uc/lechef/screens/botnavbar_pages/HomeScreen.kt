package com.uc.lechef.screens.botnavbar_pages

import android.graphics.Color.YELLOW
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.R


@Preview
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()

){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp


    //chech what this is later
    val ScrollState = rememberScrollState()

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
                    color = Color.White
                    )
                    //name of food later change
                    Text(text = "Chow Mian",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle(10),
                        color = Color.White
                    )
                    Text(text = "See Details",
                        fontWeight = FontWeight.Light,
                        color = Color.White,
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
            Text(text = "Trending Ingridients",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle(10),
//                color = Color.White
            )

            Row(
                modifier = Modifier
                    .horizontalScroll(ScrollState)
                    .fillMaxSize()

                ) {
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

                    ){
                        Image(  painter = painterResource(id = R.drawable.ingridient1),
                            contentDescription = "ingridient",
                            Modifier
                                .width(screenWidth / 4)
                                .height(screenHeight / 8)
                            ,
                            contentScale = ContentScale.Crop,
                        )
                        Text(text = "Tauge",
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                }
            }

        }

    //Trending recipes
        Column(
            modifier = Modifier
                .padding(20.dp, 20.dp, 20.dp, 0.dp)
                .fillMaxWidth()
                .clickable {
                    // navigate to details
                },
        )
        {
            Text(text = "Trending Recipes",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle(10),
//                color = Color.White
            )

            //for loopnya disini
            Column {
                    Card(
                        modifier = Modifier
                            .padding(5.dp)
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
                                Column{
                                    Text(text = "Vegan Pancakes")
                                    Row {
                                        //icon time
//                                      Icon
                                        //text time taken
                                        Text(text = "8 Minutes")
                                    }
                                    Row {
                                        //icon rating
//                                  Icon
                                        //text rating
                                        Text(text = "4.3")
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
