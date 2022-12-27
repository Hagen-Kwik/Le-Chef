package com.uc.lechef.screens.botnavbar_pages

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.Navigation.NavigationEnum

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun MyRecipesScreen(navController: NavHostController = rememberNavController()) {

    val ScrollState = rememberScrollState()
    var searchbar = remember { mutableStateOf("") }

    LazyColumn() {
        stickyHeader {

            Text(text = "My Recipes",
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

//    give items here

}
