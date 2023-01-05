package com.uc.lechef.screens.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.uc.lechef.Models.*
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

    private var _curuser  = MutableStateFlow<GetUser?>(null)
    val curuser = _curuser
    fun addCuruser(curuser: GetUser){
        _curuser.value = curuser
    }

    private var _Resepbyuser  = MutableStateFlow<ResepbyUser?>(null)
    var ResepbyuserFORCHECK = MutableStateFlow(false)
    val ResepbyUser = _Resepbyuser
    fun resepyUser(resep: ResepbyUser){
        _Resepbyuser.value = resep
    }

    private var _savedrecipe  = MutableStateFlow<saved_recipe?>(null)
    val savedrecipe = _savedrecipe
    fun addtosaved(saved_recipe: saved_recipe){
        _savedrecipe.value = saved_recipe
    }
}

