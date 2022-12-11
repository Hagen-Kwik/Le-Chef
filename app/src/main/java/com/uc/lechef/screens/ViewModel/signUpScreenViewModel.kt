package com.uc.lechef.screens.ViewModel

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.savedstate.SavedStateRegistryOwner
import com.uc.lechef.Models.user
import com.uc.lechef.graphs.AuthScreen
import com.uc.lechef.repository.userRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class signUpScreenViewModel @Inject constructor(private val repository: userRepository
): ViewModel() {

    fun registerUser(username: String, password: String, email: String): Any = {
        viewModelScope.launch {
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