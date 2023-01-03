package com.uc.lechef.screens.ViewModel

import android.util.Log
import androidx.lifecycle.*
import com.uc.lechef.Models.Resep
import com.uc.lechef.Models.User
import com.uc.lechef.repository.userRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.http.StatusLine
import javax.inject.Inject


@HiltViewModel
class signUpScreenViewModel @Inject constructor(private val repository: userRepository
): ViewModel() {

    private var _registered  = MutableStateFlow(false)
    val registered = _registered

     fun registerUser(username: String, password: String, email: String) {
        viewModelScope.launch {
            repository.RegisterForNewUser(
                User("",
                "",
                email,
                -1,
                username,

                true,
                password,
                "",
                "", ""

                )
            ).let {
//                Log.d("RESPONSE", it.toString())
//                if (response.isSuccessful) {
//                    Log.e("POST Data", "SUCCESS")
//                } else {
//                    Log.e("POST Data", "Failed")
//                }
                _registered.value = true

            }
        }
    }
}