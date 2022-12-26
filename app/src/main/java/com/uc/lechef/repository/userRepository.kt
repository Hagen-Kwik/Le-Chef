package com.uc.lechef.repository

import com.uc.lechef.Models.User
import com.uc.lechef.retrofit.EndPointApi
import javax.inject.Inject

class userRepository @Inject constructor(
    private val api: EndPointApi
    ){

    suspend fun RegisterForNewUser(user: User) = api.postUserData(user)


}