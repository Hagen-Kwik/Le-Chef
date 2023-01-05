package com.uc.lechef.screens.botnavbar_pages

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.uc.lechef.Navigation.NavigationEnum
import com.uc.lechef.helper.StoreUserID
import com.uc.lechef.screens.ViewModel.sharedAllScreenViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProfileScreen(navController: NavHostController = rememberNavController(),
        sharedViewModel: sharedAllScreenViewModel) {
    val ScrollState = rememberScrollState()

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Text(
                text = "Profile",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle(10),
                fontSize = 50.sp,
                color = Color(249,162,46),
                modifier = Modifier.padding(top = 20.dp, start = 20.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(ScrollState)
            ) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    backgroundColor = Color(0xFFd6d6c2),
                    modifier = Modifier
                        .height(screenHeight / 4)
                        .width(screenWidth / 2)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_person_24),
                        contentDescription = "homePicture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.padding(20.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Button(
                            shape = CircleShape,
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp), // Set background color here
                            colors = ButtonDefaults.buttonColors(backgroundColor =  Color(249,162,46))
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_baseline_edit_24),
                                contentDescription = "homePicture",
                                contentScale = ContentScale.Crop,
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }

                }
                Text(
                    text = sharedViewModel.curuser.value?.User?.Name!!,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle(10),
                    fontSize = 27.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )

                Text(
                    text = sharedViewModel.curuser.value?.User?.Email!!,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle(10),
                    fontSize = 20.sp,
                )

                Card(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(10.dp)
                        .width(screenWidth * 19 / 20)
                        .padding(10.dp)
                ){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_notifications_none_24),
                            contentDescription = "homePicture",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .padding(9.dp)
                        )
                        Text(
                            text = "Notifications",
                            fontStyle = FontStyle(10),
                            fontSize = 22.sp,
                        )
                        val checkedState = remember { mutableStateOf(true)  }
                        Switch(
                            checked = checkedState.value,
                            onCheckedChange = { checkedState.value = it }
                        )
                    }
                }

                val email = remember { mutableStateOf("") }
                val username = remember { mutableStateOf("") }
                var popnameControl by remember { mutableStateOf(false) }
                //dialog for update email/username
                if (popnameControl) {
                    Dialog(
                        onDismissRequest = { popnameControl = false },
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .width(screenWidth * 14 / 20)
                                .height(screenWidth * 2 /3)
                                .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Change password",
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle(10),
                                    fontSize = 20.sp,
                                    color = Color(249,162,46),
                                    modifier = Modifier.padding(top = 20.dp, start = 20.dp)
                                )
                                Spacer(modifier = Modifier.height(screenHeight/30))
                                TextField(
                                    label = { Text(text = "username") },
                                    value = username.value,
                                    onValueChange = { username.value = it },
                                    modifier = Modifier
                                        .padding(start = 20.dp, end = 20.dp)
                                )
                                Spacer(modifier = Modifier.height(screenHeight/90))
                                TextField(
                                    label = { Text(text = "Email") },
                                    value = email.value,
                                    onValueChange = { email.value = it },
                                    modifier = Modifier
                                        .padding(start = 20.dp, end = 20.dp)
                                )
                                Spacer(modifier = Modifier.height(screenHeight/60))
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(
                                        text = "cancel",
                                        fontStyle = FontStyle(10),
                                        fontSize = 20.sp,
                                        color = Color(249,162,46),
                                        modifier = Modifier
                                            .clickable {
                                                popnameControl = false
                                            }
                                    )

                                    Text(
                                        text = "Sumbit",
                                        fontStyle = FontStyle(10),
                                        fontSize = 20.sp,
                                        color = Color(249,162,46),
                                        modifier = Modifier
                                            .clickable {
                                                //logic to submit
                                                popnameControl = false
                                            }
                                    )
                                }
                            }
                        }
                    }
                }

//                Card(
//                    elevation = 8.dp,
//                    shape = RoundedCornerShape(10.dp),
//                    modifier = Modifier
//                        .width(screenWidth * 19 / 20)
//                        .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
//                        .clickable {
//                            popnameControl = true
//                        }
//                ){
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Image(
//                            painter = painterResource(id = R.drawable.ic_baseline_mail_outline_24),
//                            contentDescription = "homePicture",
//                            contentScale = ContentScale.Fit,
//                            modifier = Modifier
//                                .height(50.dp)
//                                .width(50.dp)
//                                .padding(9.dp)
//                        )
//                        Text(
//                            text = "Change Email/Username",
//                            fontStyle = FontStyle(10),
//                            fontSize = 23.sp,
//                        )
//                    }
//                }

                val password = remember { mutableStateOf("") }
                var popuppasswordControl by remember { mutableStateOf(false) }

                if (popuppasswordControl) {
                    Dialog(
                        onDismissRequest = { popuppasswordControl = false },
                    ) {
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .width(screenWidth * 14 / 20)
                                .height(screenWidth / 2)
                                .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Change password",
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle(10),
                                    fontSize = 20.sp,
                                    color = Color(249,162,46),
                                    modifier = Modifier.padding(top = 20.dp, start = 20.dp)
                                )
                                Spacer(modifier = Modifier.height(screenHeight/30))
                                TextField(
                                    label = { Text(text = "Password") },
                                    value = password.value,
                                    visualTransformation = PasswordVisualTransformation(),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                    onValueChange = { password.value = it },
                                    modifier = Modifier
                                        .padding(start = 20.dp, end = 20.dp)
                                )
                                Spacer(modifier = Modifier.height(screenHeight/40))
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(
                                        text = "cancel",
                                        fontStyle = FontStyle(10),
                                        fontSize = 20.sp,
                                        color = Color(249,162,46),
                                        modifier = Modifier
                                            .clickable {
                                                popuppasswordControl = false
                                            }
                                    )

                                    Text(
                                        text = "Sumbit",
                                        fontStyle = FontStyle(10),
                                        fontSize = 20.sp,
                                        color = Color(249,162,46),
                                        modifier = Modifier
                                            .clickable {
                                                //logic to submit
                                                popuppasswordControl = false
                                            }
                                    )
                                }
                            }
                        }
                    }
                }
                Card(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .width(screenWidth * 19 / 20)
                        .padding(start = 10.dp, end = 10.dp, bottom = 20.dp)
                        .clickable {
                            popuppasswordControl = true
                        }
                ){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_lock_open_24),
                            contentDescription = "homePicture",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .padding(9.dp)
                        )
                        Text(
                            text = "Change Password",
                            fontStyle = FontStyle(10),
                            fontSize = 23.sp,
                        )
                    }
                }

                Card(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .width(screenWidth * 19 / 20)
                        .padding(start = 10.dp, end = 10.dp)
                        .clickable {
                        navController.popBackStack(NavigationEnum.loginScreen.name, true)
                        navController.navigate(NavigationEnum.loginScreen.name)
                    }
                ){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_logout_24),
                            contentDescription = "homePicture",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp)
                                .padding(9.dp)
                        )
                        Text(
                            text = "Log out",
                            fontStyle = FontStyle(10),
                            fontSize = 23.sp,

                        )
                    }
                }
            }
        }
    }
}
