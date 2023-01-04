package com.uc.lechef.screens.ViewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uc.lechef.Models.ForMakingRecipe
import com.uc.lechef.repository.userRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class UploadRecipeScreenViewModel @Inject constructor(private val repository: userRepository,
): ViewModel() {

    var recipe_name = ""
    var recipe_time_needed = ""
    var recipe_instructions = ""
    var recipe_description = ""
    var recipe_portion = 0
    var photo = ""
    var video = ""

    fun addEachAttribute(
        name: String,
        desc: String,
        time: String,
        porsi: Int,
        intsruc: String
    ) {
         recipe_name = name
         recipe_time_needed = time
         recipe_instructions = intsruc
         recipe_description = desc
         recipe_portion = porsi
    }

    //FOR RESEP ID HARUS BIKIN RESEP E SEK
    var FORARRAYresepID = -1

    var eachBahanArray = arrayOfNulls<String>(2)
    var allBahanArray = arrayListOf<Array<String?>>()

    fun addBahanToEachArray(bahanID: Int, jumlahbahan: String) {
        eachBahanArray[0] = bahanID.toString()
        if (jumlahbahan == ""){
            eachBahanArray[1] = "secukupnya"
        }
        eachBahanArray[1] = jumlahbahan
        allBahanArray.add(eachBahanArray)
    }

    var forIdResep = -1
    val mediaType = "application/json; charset=utf-8".toMediaType()

    fun createNewRecipe(COOKIE: Flow<String?>, USERID: Flow<String?>, ) {
        viewModelScope.launch {
            COOKIE.collect { cookie ->
                if (cookie != null) {
                    USERID.collect { id ->
                        if (id != null) {

                            val json = JSONObject()
                            json.put("Created_by", id.toInt())
                            json.put("jumlahrating", 0)
                            json.put("Rating", 0)
                            json.put("Description", recipe_description)
                            json.put("Judul", recipe_name)
                            json.put("Portionsize", recipe_portion)
                            json.put("Foto", "FOTO")
                            json.put("Video", "")
                            json.put("Timetaken", recipe_time_needed)
                            json.put("Steps", recipe_instructions)

                            val requestBody = json.toString().toRequestBody(mediaType)

                            Log.d("json", json.toString())
                            Log.d("cookie", cookie)




                            repository.createResep(cookie, ForMakingRecipe(id.toInt(),
                                recipe_description,"FOTO",recipe_name,recipe_portion,0,
                                recipe_instructions,recipe_time_needed,"",0)
                            ).let { response ->
                                forIdResep = response.body()?.id ?: -1

                            }

                            for (item in allBahanArray) {
                                val json = JSONObject()
                                json.put("Resep_id", id.toInt())
                                item[0]?.let { json.put("Bahan_id", it.toInt()) }
                                json.put("Jumlahbahan", item[1].toString())

                                val requestBody = json.toString().toRequestBody(mediaType)

                                repository.createListBahan(cookie, requestBody)
                            }
                        }
                    }
                }
            }
            //go to back stack complete give new variable
        }
    }
}
