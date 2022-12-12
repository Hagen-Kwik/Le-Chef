package com.uc.lechef


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.graphs.RootNavigationGraph
import com.uc.lechef.screens.ViewModel.signUpScreenViewModel
import com.uc.lechef.ui.theme.LeChefTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    private lateinit var viewModel: signUpScreenViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        viewModel = ViewModelProvider(this).get(signUpScreenViewModel::class.java)

        setContent {
            LeChefTheme {
//                RootNavigationGraph(navController = rememberNavController(), viewModel)
                RootNavigationGraph(navController = rememberNavController())
            }
        }

    }
}




