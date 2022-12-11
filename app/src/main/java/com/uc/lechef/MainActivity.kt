package com.uc.lechef


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.uc.lechef.graphs.RootNavigationGraph
import com.uc.lechef.ui.theme.LeChefTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LeChefTheme {
                RootNavigationGraph(navController = rememberNavController())
            }
        }

    }
}




