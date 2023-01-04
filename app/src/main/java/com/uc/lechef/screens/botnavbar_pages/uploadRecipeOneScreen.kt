package com.uc.lechef.screens.botnavbar_pages

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.Navigation.NavigationEnum
import com.uc.lechef.screens.ViewModel.UploadRecipeScreenViewModel
import com.uc.lechef.screens.ViewModel.sharedAllScreenViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun uploadRecipeOneScreen(navController: NavHostController = rememberNavController(),
                          sharedViewModel: sharedAllScreenViewModel,
                          UploadRecipeViewModel : UploadRecipeScreenViewModel
                            ) {


    val ScrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
        .verticalScroll(ScrollState)
    ) {
        
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
            .fillMaxSize()
        ){
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


        val name = remember { mutableStateOf("") }
        Text(text = "Name")
        TextField(value = name.value, onValueChange = {
            name.value = it

        },
            placeholder = { Text("Name of recipe...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 5.dp)
        )


        val desc = remember { mutableStateOf("") }
        Text(text = "Description of Food")
        TextField(value = desc.value, onValueChange = {
            desc.value = it

        },
            placeholder = { Text("The food's Description of your recipe...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 5.dp)
                .height(100.dp)

        )

        var jumlahBahanINVIEW by remember {
            mutableStateOf("")
        }

        var ID by remember {
            mutableStateOf(-1)
        }

        var selectedOptionText by remember {
            mutableStateOf("")
        }

        Text(text = "Ingredients")

        var expanded by remember {
            mutableStateOf(false)
        }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                label = { Text("Ingredients") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                for(item in sharedViewModel.IngredientsTrending.value?.Bahan!!)
                    DropdownMenuItem(
                        onClick = {
                            ID = item.ID
                            selectedOptionText = item.Namabahan
                            expanded = false
                        }
                    ) {
                        Text(text = item.Namabahan)
                    }
            }
        }

        val jumlahbahan = remember { mutableStateOf("") }

            TextField(value = jumlahbahan.value, onValueChange = {
                jumlahbahan.value = it
            },
                placeholder = { Text("Jumlah Bahan") },
                modifier = Modifier
                    .padding(0.dp, 5.dp)
                    .fillMaxWidth()
            )

            Button(
                onClick = {
                    Log.d("MASUK GA YA 1" , ID.toString())
                    Log.d("MASUK GA YA 2" , jumlahBahanINVIEW)

                    UploadRecipeViewModel.addBahanToEachArray(ID, jumlahbahan.value)
                }
            ) {
                Text(text = "Add")
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null,
                )
            }


        val time = remember { mutableStateOf("") }

        Text(text = "Time Needed")
        TextField(value = time.value, onValueChange = {
            time.value = it
        },
            placeholder = { Text("Time Taken e.g (2 hours)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 5.dp)


        )


        val porsi = remember { mutableStateOf(0) }

        Text(text = "Recipe Portion")
        TextField(value = porsi.value.toString(), onValueChange = {
            porsi.value = it.toInt()
        },
            placeholder = { Text(" e.g 3 servings") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 5.dp)


        )

        val instruc = remember { mutableStateOf("") }

        Text(text = "Instructions")
        TextField(value = instruc.value, onValueChange = {
            instruc.value = it

        },
            placeholder = { Text("Cooking Instructions") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 5.dp)
                .height(300.dp)
        )
        
        Button(onClick = {
            UploadRecipeViewModel.addEachAttribute(name.value, desc.value, time.value, porsi.value, instruc.value)
            navController.navigate(NavigationEnum.uploadRecipeTwoScreen.name)
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor =  Color(249,162,46))
        ) {
            Text(text = "Next")
        }


    }
}

