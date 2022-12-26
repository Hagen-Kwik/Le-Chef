package com.uc.lechef.retrofit

import com.google.gson.JsonObject
import com.uc.lechef.Models.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPointApi {



    @GET("/users/{id}")
    suspend fun getUserData(
        @Path("id") id: Int,
//        @Query("api_key") apiKey: String
    ): retrofit2.Response<JsonObject>


    @POST("/users")
    suspend fun postUserData(
        @Body user: User
    ): retrofit2.Response<JsonObject>


}