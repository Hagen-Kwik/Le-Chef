package com.uc.lechef.screens.ViewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uc.lechef.Models.ForMakingRecipe
import com.uc.lechef.Models.ForUploadToListBahan
import com.uc.lechef.Models.ResepbyUser
import com.uc.lechef.Models.TempForRecipeScreenBahan
import com.uc.lechef.repository.userRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class UploadRecipeScreenViewModel @Inject constructor(private val repository: userRepository,
): ViewModel() {

    var FINISHED  = MutableStateFlow(false)

    var recipe_name = ""
    var recipe_time_needed = ""
    var recipe_instructions = ""
    var recipe_description = ""
    var recipe_portion = 0
    var recipe_calories = 0
    var photo = ""
    var video = ""

    fun addEachAttribute(
        name: String,
        desc: String,
        time: String,
        calories : Int,
        porsi: Int,
        intsruc: String
    ) {
         recipe_name = name
         recipe_time_needed = time
         recipe_instructions = intsruc
         recipe_description = desc
         recipe_portion = porsi
        recipe_calories = calories
    }

    fun addfrompage2(
        foto: String
    ){
        photo = foto
    }

    //FOR RESEP ID HARUS BIKIN RESEP E SEK
    var FORARRAYresepID = -1

    var allBahanArray = arrayListOf<TempForRecipeScreenBahan>()

    private var _arraylistBahanMutableState  = MutableStateFlow(false)
    val arraylistBahanMutableState = _arraylistBahanMutableState

    fun addBahanToEachArray(bahanID: Int, jumlahbahan: String, nama :String) {

        if (jumlahbahan == ""){
            allBahanArray.add(TempForRecipeScreenBahan(bahanID, "Secukupnya", nama))
        } else {
            allBahanArray.add(TempForRecipeScreenBahan(bahanID, jumlahbahan, nama))
        }
        _arraylistBahanMutableState.value = true

    }


    fun deleteBahanFromArray(int: Int){
        allBahanArray.removeAt(int)
        _arraylistBahanMutableState.value = true
    }

    var forIdResep = -1
    var ResepbyTHISuser: MutableStateFlow<ResepbyUser?> = MutableStateFlow(null)

    fun createNewRecipe(COOKIE: Flow<String?>, USERID: Flow<String?>, ) {
        viewModelScope.launch {
            COOKIE.collect { cookie ->
                if (cookie != null) {
                    USERID.collect { id ->
                        if (id != null) {
                            repository.createResep(cookie, ForMakingRecipe(id.toInt(),
                                recipe_description,photo,recipe_name,recipe_portion,recipe_calories,0,
                                recipe_instructions,recipe_time_needed,"",0)
                            ).let { response ->
                                forIdResep = response.body()?.id ?: -1

                            }

                            for (item in allBahanArray) {
                                Log.d("ITEMSSSSSSSSSSS", item.toString())
                                Log.d("FORIDRESEPP", forIdResep.toString())

                                repository.createListBahan(cookie, ForUploadToListBahan(item.bahanID, item.jumlahbahan,forIdResep))
                            }


                            repository.getResepbyUser(id.toInt(),cookie)
                                .let { response ->
                                    ResepbyTHISuser.value = response.body()
                                }

                            FINISHED.value = true

                        }
                    }
                }
            }
            //go to back stack complete give new variable
        }
    }
}
