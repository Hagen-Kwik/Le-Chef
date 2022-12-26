package com.uc.lechef.screens


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration.Companion.Underline
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uc.lechef.Navigation.NavigationEnum
import com.uc.lechef.R
import com.uc.lechef.screens.ViewModel.signUpScreenViewModel


@Composable
fun SignUpPage (navController: NavController, viewModel: signUpScreenViewModel
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(0.dp)

        ) {
            Text(text = "Already Have an Account?",
                modifier = Modifier
                    .padding(20.dp,20.dp,10.dp,20.dp)

            )
            Text(
                text = "Log In",
                modifier = Modifier
                    .padding(0.dp, 20.dp, 20.dp, 20.dp)
                .clickable {
                navController.navigate(NavigationEnum.loginScreen.name)
            },
                style = TextStyle(
                    textDecoration = Underline,
                    color = androidx.compose.ui.graphics.Color.Blue
                    )


            )
        }
    }
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        val email = remember { mutableStateOf(TextFieldValue()) }
        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }


        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(100.dp,30.dp,100.dp, 30.dp)
            )
        Text(text = "Le Chéf",
            style = TextStyle(fontSize = 40.sp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(text = "Sign Up", style = TextStyle(fontSize = 25.sp),
        textAlign = TextAlign.Start)


        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            label = { Text(text = "Email") },
            value = email.value,
            onValueChange = { email.value = it },
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            label = { Text(text = "Username") },
            value = username.value,
            onValueChange = { username.value = it },
            modifier = Modifier
                .fillMaxWidth()
        )

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
                  viewModel.registerUser(username.value.toString(),
                  password.value.text,
                  email.value.toString())
                      Log.d("HERE", "MASUK")},
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)

        ) {
            Text(text = "Sign Up")
        }

    }
}

