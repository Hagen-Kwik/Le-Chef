package com.uc.lechef.screens.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.uc.lechef.Models.ResepSpecific
import com.uc.lechef.Models.User
import com.uc.lechef.Models.bahanAll
import com.uc.lechef.Models.resepAll
import com.uc.lechef.repository.userRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class sharedAllScreenViewModel @Inject constructor(private val repository: userRepository
): ViewModel() {

    //home funcs
    private var _RecipeOfTheDay  = MutableStateFlow<ResepSpecific?>(null)
    val RecipeOfTheDay = _RecipeOfTheDay
    var RecipeoftheDayFORCHECK = MutableStateFlow(false)
    fun addRecipeofTheDay(ResepSpecific: ResepSpecific){
        _RecipeOfTheDay.value = ResepSpecific
    }

    private var _IngredientsTrending  = MutableStateFlow<bahanAll?>(null)
    val IngredientsTrending = _IngredientsTrending
    var IngredientsTrendingFORCHECK = MutableStateFlow(false)
    fun addBahanAll(bahanAll: bahanAll){
        _IngredientsTrending.value = bahanAll
    }

    private var _RecipeTrending  = MutableStateFlow<resepAll?>(null)
    val RecipeTrending = _RecipeTrending
    var RecipeTrendingFORCHECK = MutableStateFlow(false)
    fun addResepAll(resepAll: resepAll){
        _RecipeTrending.value = resepAll
    }

    private var _DetailedRecipe  = MutableStateFlow<ResepSpecific?>(null)
    val DetailedRecipe = _DetailedRecipe
    var DetailedRecipeFORCHECK = MutableStateFlow(false)
    fun addDetailedRecipe(ResepSpecific: ResepSpecific){
        _DetailedRecipe.value = ResepSpecific
    }

    private var _curuser  = MutableStateFlow<User?>(null)
    val curuser = _curuser
    fun addCuruser(curuser: User){
        _curuser.value = curuser
    }
}

