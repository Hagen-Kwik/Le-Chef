package com.uc.lechef.screens.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uc.lechef.Models.*
import com.uc.lechef.helper.StoreUserCookie
import com.uc.lechef.repository.userRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: userRepository,
): ViewModel() {
    private var _added  = MutableStateFlow(false)
    val added = _added

    private var _changedToDetailed  = MutableStateFlow(false)
    val changedToDetailed = _changedToDetailed

    private var _vieweddata  = MutableStateFlow(false)
    val vieweddata = _vieweddata

    var  savedrecipe: MutableStateFlow<SavedRecipePerUser?> = MutableStateFlow(null)
    var resepSpecific: MutableStateFlow<ResepSpecific?> = MutableStateFlow(null)

    fun moveToDetailed(int: Int, COOKIE: Flow<String?>) {

        viewModelScope.launch {
            COOKIE.collect{
                if (it != null) {
                    repository.get1Recipe(int,it).let { response ->
                    resepSpecific.value = response.body()
                    _changedToDetailed.value = true
                    }
                }
            }

        }
    }

    var UserSavedRecipe: MutableStateFlow<SavedRecipePerUser?> = MutableStateFlow(null)

    fun addtosaved(resepid: Int,userid: Int, COOKIE: Flow<String?>,) {

        viewModelScope.launch {
            COOKIE.collect{
                if (it != null) {
                    Log.d("created", createsavedrecipe(resepid,userid).toString())
                    repository.addtosaved(
                        it,createsavedrecipe(resepid,userid)
                    )

                    repository.getUserSavedRecipe(userid ,it)
                        .let { response ->
                            UserSavedRecipe.value = response.body()
                            Log.d("HOEMSCREEN VIEWMODEL", response.body().toString())
                        }

                    _added.value = true

                }


            }
        }
    }

    fun readsavedrecipe(userid: Int, COOKIE: Flow<String?>) {
        viewModelScope.launch {
            COOKIE.collect{
                if (it != null) {
                    repository.getsavedbyuser(userid,it).let { response ->
                        savedrecipe.value = response.body()
                        _vieweddata.value = true
                    }
                }
            }
        }
    }
}