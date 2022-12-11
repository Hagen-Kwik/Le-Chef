package com.uc.lechef.Navigation

import androidx.annotation.DrawableRes
import com.uc.lechef.R

sealed class NavigationItem(val id: Int, var title: String, @DrawableRes var Selectedicon:Int, @DrawableRes var unSelectedIcon:Int){
    object Home: NavigationItem(0, "home", R.drawable.botnavbarhome, R.drawable.botnavbarhome)
    object MyRecipes: NavigationItem(1, "myrecipes", R.drawable.botnavbarmyrecipes, R.drawable.botnavbarmyrecipes)
    object LikedRecipes: NavigationItem(2, "likedrecipes", R.drawable.botnavbarlikedrecipe, R.drawable.botnavbarlikedrecipe)
    object Profile: NavigationItem(3, "profile", R.drawable.botnavbarprofile, R.drawable.botnavbarprofile)
}
