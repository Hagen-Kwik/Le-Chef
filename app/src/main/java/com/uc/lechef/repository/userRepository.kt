package com.uc.lechef.repository

import com.uc.lechef.Models.User
import com.uc.lechef.Models.createsavedrecipe
import com.uc.lechef.retrofit.EndPointApi
import okhttp3.RequestBody
import javax.inject.Inject

class userRepository @Inject constructor(
    private val api: EndPointApi
    ){

    suspend fun RegisterForNewUser(user: User) = api.postUserData(user)

    suspend fun login(RequestBody: RequestBody) = api.login(RequestBody)

    suspend fun get1Recipe(id: Int, authkey:String) = api.getSpecificResep(id, authkey)

    suspend fun getBahanAll(authkey:String) = api.getBahanAll(authkey)

    suspend fun getRecipeAll(authkey:String) = api.getResepAll(authkey)

    suspend fun getUserspes(id: Int, authkey:String) = api.getUserspes(id, authkey)

    suspend fun getResepbyUser(id: Int, authkey:String) = api.getResepbyuser(id, authkey)

//    suspend fun addtosaved(authkey:String,createsavedrecipe: createsavedrecipe) = api.createSavedRecipe(authkey, createsavedrecipe)

    suspend fun addtosaved(authkey:String,createsavedrecipe: createsavedrecipe) = api.createSavedRecipe(authkey,createsavedrecipe)
    suspend fun getsavedbyuser(id: Int, authkey:String) = api.getsavedbyuser(id, authkey)
}
