package com.uc.lechef.screens.ViewModel

import android.util.Log
import androidx.lifecycle.*
import com.uc.lechef.Models.user
import com.uc.lechef.repository.userRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class signUpScreenViewModel @Inject constructor(private val repository: userRepository
): ViewModel() {

    fun registerUser(username: String, password: String, email: String){
        viewModelScope.launch {
            Log.e("MASUK LAUNCH Data", "MASUKKKKK")

            repository.RegisterForNewUser(user(username, email, password, "", "", true)).let {
                    response ->
                if (response.isExecuted){
                    Log.e("POST Data", "SUCCESS")
                } else {
                    Log.e("POST Data", "Failed")
                }
            }
        }
    }

}