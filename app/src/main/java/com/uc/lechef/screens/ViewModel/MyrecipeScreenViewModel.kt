package com.uc.lechef.screens.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uc.lechef.Models.ResepSpecific
import com.uc.lechef.Models.saved_recipe
import com.uc.lechef.repository.userRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyrecipeScreenViewModel @Inject constructor(private val repository: userRepository,
): ViewModel() {

    private var _changedToDetailed  = MutableStateFlow(false)
    val changedToDetailed = _changedToDetailed

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
}