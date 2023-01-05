package com.uc.lechef.screens.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uc.lechef.Models.*
import com.uc.lechef.repository.userRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchByIngredientsScreenViewModel @Inject constructor(private val repository: userRepository
): ViewModel() {

    var bahan_bahanArray = arrayListOf<String>()

    fun addBahanToArray(String: String){
        bahan_bahanArray.add(String)
    }

    var searchRecipe: MutableStateFlow<ResepFromBahanSearch?> = MutableStateFlow(null)

    private var _changedToSearchRecipe = MutableStateFlow(false)
    val changedToSearchRecipe = _changedToSearchRecipe






    fun searchRecipe(COOKIE: Flow<String?>) {
        viewModelScope.launch {
            COOKIE.collect{
                if (it != null) {
                    repository.getResepFromBahan(ForUploadToFindingResepWithBahan(bahan_bahanArray), it
                    ).let { response ->
                        searchRecipe.value = response.body()
                        _changedToSearchRecipe.value = true
                    }
                }
            }
        }
    }
}