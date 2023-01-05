package com.uc.lechef.screens.botnavbar_pages

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.R
import com.uc.lechef.helper.StoreUserCookie
import com.uc.lechef.helper.StoreUserID
import com.uc.lechef.screens.ViewModel.UploadRecipeScreenViewModel
import com.uc.lechef.screens.ViewModel.sharedAllScreenViewModel
import java.io.ByteArrayOutputStream

@Composable
fun uploadRecipeTwoScreen(navController: NavHostController = rememberNavController(),
                          sharedViewModel: sharedAllScreenViewModel,
                          UploadRecipeViewModel : UploadRecipeScreenViewModel
) {

    val ScrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current

    val bitmap =  remember {
        mutableStateOf<Bitmap?>(null)
    }

    val bytearraystring64 = remember { mutableStateOf("") }



//    val bitmap2: Bitmap = BitmapFactory.decodeByteArray(tes, 0, tes.size)



    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
        .verticalScroll(ScrollState)
    ) {
        val launcher = rememberLauncherForActivityResult(contract =
        ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }

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
                .height(screenHeight / 4).clickable {
                    launcher.launch("image/*") },
            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Center,


                ) {
                Text(text = "Upload Photos")
//                Image(painter = painterResource(id = R.drawable.icon_picture_placeholder),
//                    contentDescription = "",
//                    modifier = Modifier.width(screenWidth / 4).padding(5.dp),
//                    contentScale = ContentScale.FillWidth,
//                    )
                imageUri?.let {
                    val source = ImageDecoder
                        .createSource(context.contentResolver,it)
                    bitmap.value = ImageDecoder.decodeBitmap(source)



                    val stream = ByteArrayOutputStream()
                    bitmap.value?.compress(Bitmap.CompressFormat.PNG,90,stream)
                    val tes:ByteArray = stream.toByteArray()

                    val bitmap2: Bitmap = BitmapFactory.decodeByteArray(tes, 0, tes.size)
                    val imageBitmap: ImageBitmap = bitmap2.asImageBitmap()

                    bytearraystring64.value = Base64.encodeToString(tes, Base64.DEFAULT)
                    Image(
                        imageBitmap,
                        contentDescription = "",
                        modifier = Modifier.width(screenWidth / 4).padding(5.dp),
                        contentScale = ContentScale.FillWidth)
                }
                Text(text = "You can upload 1 photo")
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
                Text(text = "Upload Video")
                Image(painter = painterResource(id = R.drawable.icon_video_placeholder),
                    contentDescription = "",
                    modifier = Modifier.width(screenWidth / 4).padding(5.dp),
                    contentScale = ContentScale.FillWidth,
                    )
                Text(text = "You can upload only 1 link")
            }

        }
    }


    val COOKIE = StoreUserCookie(context)
    val USERID = StoreUserID(context)

    Row(
        verticalAlignment = Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Button(onClick = {
            UploadRecipeViewModel.addfrompage2(bytearraystring64.value)
            UploadRecipeViewModel.createNewRecipe(COOKIE.getCookie,USERID.getUserId)
//            navController.popBackStack()
//            navController.popBackStack()
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


