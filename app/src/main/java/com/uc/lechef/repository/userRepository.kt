package com.uc.lechef.repository

import com.uc.lechef.Models.user
import com.uc.lechef.retrofit.EndPointApi
import javax.inject.Inject

class userRepository @Inject constructor(private val api: EndPointApi){

    suspend fun RegisterForNewUser(user: user) = api.postUserData(user)


}