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

    var nambah = MutableStateFlow(false)
    var delete = MutableStateFlow(false)

    fun addBahanToArray(String: String){
         var temp = bahan_bahanArray.toArray()
        if (temp.isEmpty()){
            bahan_bahanArray.add(String)
        } else {
                var adaGA = false
            for (each in temp) {
                if (each == String) {
                    adaGA = true
                    break
                } else {
                    adaGA = false
                }

            }
                if (!adaGA){
                    bahan_bahanArray.add(String)
                }
        }
        nambah.value = true
    }

    fun deleteBahan(int: Int){
        bahan_bahanArray.removeAt(int)
        delete.value = true
    }

    var searchRecipe: MutableStateFlow<ResepFromBahanSearch?> = MutableStateFlow(null)

    private var _changedToSearchRecipe = MutableStateFlow("notsearched")
    val changedToSearchRecipe = _changedToSearchRecipe



    fun searchRecipe(COOKIE: Flow<String?>) {
        viewModelScope.launch {
            COOKIE.collect{
                if (it != null) {
                    if (bahan_bahanArray.isEmpty()) {
                        _changedToSearchRecipe.value = "nodata"
                    } else {
                        repository.getResepFromBahan(ForUploadToFindingResepWithBahan(
                            bahan_bahanArray), it
                        ).let { response ->
                            bahan_bahanArray.clear()
                            searchRecipe.value = response.body()
                            _changedToSearchRecipe.value = "searched"
                        }
                    }
                }
            }
        }
    }
}