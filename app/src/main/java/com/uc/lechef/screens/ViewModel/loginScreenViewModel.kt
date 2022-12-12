package com.uc.lechef.screens.ViewModel

import androidx.lifecycle.ViewModel
import com.uc.lechef.repository.userRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class loginScreenViewModel @Inject constructor(private val repository: userRepository
): ViewModel() {}