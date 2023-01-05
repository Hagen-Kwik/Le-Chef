package com.uc.lechef.screens.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uc.lechef.Models.*
import com.uc.lechef.helper.StoreUserCookie
import com.uc.lechef.repository.userRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class loginScreenViewModel @Inject constructor(private val repository: userRepository,
): ViewModel() {

    val mediaType = "application/json; charset=utf-8".toMediaType()

    var  mainRecipeAtTopHomeHeader: MutableStateFlow<ResepSpecific?> = MutableStateFlow(null)
    var  IngredientsAtHome: MutableStateFlow<bahanAll?> = MutableStateFlow(null)
    var RecipesAtHome: MutableStateFlow<resepAll?> = MutableStateFlow(null)
    var curUser: MutableStateFlow<GetUser?> = MutableStateFlow(null)
    var Resepbyuser: MutableStateFlow<ResepbyUser?> = MutableStateFlow(null)
    var UserSavedRecipe: MutableStateFlow<SavedRecipe?> = MutableStateFlow(null)


    private var _logged  = MutableStateFlow(false)
    val logged = _logged

    var userid =  ""
    var token = "authorization="
    fun login(email: String, password: String) {

        val json = JSONObject()
        json.put("Email", email)
        json.put("Password", password)

        val requestBody = json.toString().toRequestBody(mediaType)

        viewModelScope.launch {
            repository.login(requestBody).let { response ->
                if ((response.body()?.status.toString() ?: 400) == "200") {
                    token = (token + response.body()?.token) ?: "authorization="
                    userid = (response.body()?.user.toString() ?: "")
                    if (token != "authorization=") {
                        repository.get1Recipe(1,
                            token)
                            .let { response ->
                                mainRecipeAtTopHomeHeader.value = response.body()!!

                            }

                        repository.getBahanAll(token)
                            .let { response ->
                                IngredientsAtHome.value = response.body()
                            }

                        repository.getRecipeAll(token)
                            .let { response ->
                                RecipesAtHome.value = response.body()
                            }

                        repository.getUserspes(userid.toInt(),token)
                            .let { response ->
                                curUser.value = response.body()
                            }

                        repository.getResepbyUser(userid.toInt(),token)
                            .let { response ->
                                Resepbyuser.value = response.body()
                            }

                        repository.getUserSavedRecipe(userid.toInt() ,token)
                            .let { response ->
                                UserSavedRecipe.value = response.body()
                            }

                        _logged.value = true

                    }

                }
            }
        }
    }
}