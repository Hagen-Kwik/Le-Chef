package com.uc.lechef.screens.ViewModel

import androidx.lifecycle.ViewModel
import com.uc.lechef.repository.userRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UploadRecipeScreenViewModel @Inject constructor(private val repository: userRepository,
): ViewModel() {

    var recipe_name = ""
    var recipe_time_needed = ""
    var recipe_instructions = ""
    var recipe_description = ""
    var photo = ""
    var video = ""

    //FOR RESEP ID HARUS BIKIN RESEP E SEK
    var FORARRAYresepID = -1

    var eachBahanArray = arrayOfNulls<String>(2)
    var allBahanArray = arrayListOf<Array<String?>>()

    fun addBahanToEachArray(bahanID: Int, jumlahbahan: String){
        eachBahanArray[0] = bahanID.toString()
        eachBahanArray[1] = jumlahbahan
        allBahanArray.add(eachBahanArray)
    }

}