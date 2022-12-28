package com.uc.lechef.screens.botnavbar_pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.ArrowBack
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
import com.uc.lechef.screens.botnavbar_pages.ui.theme.LeChefTheme

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun searchByIngredientsScreen(navController: NavHostController = rememberNavController()) {


    val ScrollState = rememberScrollState()
    var searchbar = remember { mutableStateOf("") }

    LazyColumn() {
        stickyHeader {

            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ){
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

    Row() {
        // see items
        Column() {

        }

        // items in pot
        Column() {

        }

        // search
        Column() {
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








