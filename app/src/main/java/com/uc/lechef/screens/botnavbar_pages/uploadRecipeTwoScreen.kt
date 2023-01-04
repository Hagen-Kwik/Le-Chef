package com.uc.lechef.screens.botnavbar_pages

import android.text.Layout.Alignment
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.Navigation.NavigationEnum
import com.uc.lechef.R
import com.uc.lechef.screens.ViewModel.UploadRecipeScreenViewModel
import com.uc.lechef.screens.ViewModel.sharedAllScreenViewModel

@Composable
fun uploadRecipeTwoScreen(navController: NavHostController = rememberNavController(),
                          sharedViewModel: sharedAllScreenViewModel,
                          UploadRecipeViewModel : UploadRecipeScreenViewModel
) {

    val ScrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp



    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
        .verticalScroll(ScrollState)
    ) {

        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )

            Text(text = "Upload Your Recipe")

            Spacer(modifier = Modifier.width(0.dp))

        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(screenHeight / 4),
            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Center,


                ) {
                Text(text = "Upload Photos")
                Image(painter = painterResource(id = R.drawable.icon_picture_placeholder),
                    contentDescription = "",
                    modifier = Modifier.width(screenWidth / 4).padding(5.dp),
                    contentScale = ContentScale.FillWidth,
                    )
                Text(text = "You can upload up to 3 photos")
            }

        }


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(screenHeight / 4),
            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = CenterHorizontally,
            ) {
                Text(text = "Upload Photos")
                Image(painter = painterResource(id = R.drawable.icon_video_placeholder),
                    contentDescription = "",
                    modifier = Modifier.width(screenWidth / 4).padding(5.dp),
                    contentScale = ContentScale.FillWidth,
                    )
                Text(text = "You can upload only 1 video / link")
            }

        }
    }

    Row(
        verticalAlignment = Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Button(onClick = {
            navController.navigate(NavigationEnum.uploadRecipeTwoScreen.name)
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor =  Color(249,162,46))
        ) {
            Text(text = "Finish"
                ,color = White
            )
        }
    }
}


