package com.uc.lechef.screens.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uc.lechef.helper.StoreUserCookie
import com.uc.lechef.repository.userRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: userRepository,
): ViewModel() {

    fun moveToDetailed(int: Int) {


        viewModelScope.launch {
            repository.get1Recipe(int,StoreUserCookie.cookie_User.toString()).let { response ->

            }
        }
    }
}