package com.uc.lechef.retrofit

import com.google.gson.JsonObject
import com.uc.lechef.Models.*
import okhttp3.Cookie
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface EndPointApi {

//    @GET("/users/{id}")
//    suspend fun getUserData(
//        @Path("id") id: Int,
////        @Query("api_key") apiKey: String
//    ): retrofit2.Response<JsonObject>


    @POST("/users")
    suspend fun postUserData(
        @Body user: User,
    )

    @POST("/login")
    suspend fun login(
        @Body data: RequestBody,
    ): retrofit2.Response<Login>

    @GET("/resep/{id}")
    suspend fun getSpecificResep(
        @Path("id") id: Int,
        @Header("Cookie") token:String
    ): retrofit2.Response<ResepSpecific>

    @GET("/bahan/")
    suspend fun getBahanAll(
        @Header("Cookie") token:String
    ): retrofit2.Response<bahanAll>

    @GET("/resep")
    suspend fun getResepAll(
        @Header("Cookie") token:String
    ): retrofit2.Response<resepAll>


    @GET("/users/{id}")
    suspend fun getUserspes(
        @Path("id") id: Int,
        @Header("Cookie") token:String
    ): retrofit2.Response<GetUser>




//    @POST("/users")
//    suspend fun postUserData(
//        @Body user: User
//    ): retrofit2.Response<JsonObject>

}