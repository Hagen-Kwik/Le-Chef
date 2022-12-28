package com.uc.lechef.screens.botnavbar_pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
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
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Preview(showBackground = true)
@Composable
fun ProfileScreen(navController: NavHostController = rememberNavController()) {
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
                    text = "John doe",
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle(10),
                    fontSize = 27.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )

                Text(
                    text = "john@gmail.com",
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle(10),
                    fontSize = 20.sp,
                )

                Card(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.padding(10.dp).width(screenWidth*19/20).padding(10.dp)
                ){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_notifications_none_24),
                            contentDescription = "homePicture",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.height(50.dp).width(50.dp).padding(9.dp)
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

                Card(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.width(screenWidth*19/20).padding(start= 10.dp, end = 10.dp, bottom = 20.dp)
                ){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_mail_outline_24),
                            contentDescription = "homePicture",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.height(50.dp).width(50.dp).padding(9.dp)
                        )
                        Text(
                            text = "Change Email/Username",
                            fontStyle = FontStyle(10),
                            fontSize = 23.sp,
                        )
                    }
                }

                Card(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.width(screenWidth*19/20).padding(start= 10.dp, end = 10.dp, bottom = 20.dp)
                ){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_lock_open_24),
                            contentDescription = "homePicture",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.height(50.dp).width(50.dp).padding(9.dp)
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
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.width(screenWidth*19/20).padding(start= 10.dp, end = 10.dp)
                ){
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_logout_24),
                            contentDescription = "homePicture",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.height(50.dp).width(50.dp).padding(9.dp)
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