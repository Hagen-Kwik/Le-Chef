package com.uc.lechef.screens


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uc.lechef.Navigation.NavigationEnum
import com.uc.lechef.R
import com.uc.lechef.helper.StoreUserCookie
import com.uc.lechef.helper.StoreUserID
import com.uc.lechef.screens.ViewModel.loginScreenViewModel
import com.uc.lechef.screens.ViewModel.sharedAllScreenViewModel

@Composable
fun LoginPage (navController: NavController, viewModel: loginScreenViewModel, sharedViewModel: sharedAllScreenViewModel) {

    val context = LocalContext.current
    val USERID =  StoreUserID(context)
    val COOKIE = StoreUserCookie(context)

    LaunchedEffect(key1 = viewModel.logged.collectAsState().value){
        if (viewModel.logged.value) {
            viewModel.mainRecipeAtTopHomeHeader.value.let {
                if (it != null) {
                    sharedViewModel.addRecipeofTheDay(it)
                }
            }

            viewModel.IngredientsAtHome.value.let {
                if (it != null) {
                    sharedViewModel.addBahanAll(it)
                }
            }

            viewModel.RecipesAtHome.value.let {
                if (it != null) {
                    sharedViewModel.addResepAll(it)
                }
            }

            viewModel.curUser.value.let {
                if (it != null) {
                    sharedViewModel.addCuruser(it)
                }
            }

            viewModel.Resepbyuser.value.let {
                if (it != null) {
                    sharedViewModel.resepyUser(it)
                }
            }

            USERID.setUserId(viewModel.userid)
            COOKIE.setCookie(viewModel.token)

            navController.navigate(NavigationEnum.botnavbar.name)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(0.dp)

        ) {
            Text(text = "Don't Have an Account?",
                modifier = Modifier
                    .padding(20.dp,20.dp,10.dp,20.dp)

            )
            Text(
                text = "Sign Up",
                modifier = Modifier
                    .padding(0.dp, 20.dp, 20.dp, 20.dp)
                    .clickable {
                        navController.navigate(NavigationEnum.signUpScreen.name)
                    },
                style = TextStyle(
                    textDecoration = TextDecoration.Underline,
                    color = androidx.compose.ui.graphics.Color.Blue
                )


            )
        }
    }



    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(100.dp, 30.dp, 100.dp, 30.dp)
        )
        Text(text = "Le Chéf",
            style = TextStyle(fontSize = 40.sp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        Text(text = "Log in", style = TextStyle(fontSize = 25.sp),
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Email") },
            value = username.value,
            onValueChange = { username.value = it },
        modifier = Modifier
            .fillMaxWidth())

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it },
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    viewModel.login(username.value,password.value)
//                    navController.navigate(NavigationEnum.botnavbar.name)

                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)

            ) {
                Text(text = "Login")
            }


        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("Forgot password?"),
            onClick = { },
            style = TextStyle(
                fontSize = 14.sp,
            )
        )
    }
}