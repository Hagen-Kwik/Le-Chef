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

    @POST("/savedrecipe")
    suspend fun createSavedRecipe(
        @Header("Cookie") token:String,
        @Body saved_recipe: createsavedrecipe,
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

    @GET("/resep/user/{id}")
    suspend fun getResepbyuser(
        @Path("id") id: Int,
        @Header("Cookie") token:String
    ): retrofit2.Response<ResepbyUser>

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

//    @PUT("/users/{id}")
//    suspend fun updateuser(
//        @Path("id") id: Int,
//        @Body data: RequestBody,
//    ): retrofit2.Response<Login>

//
//    @POST("/resep")
//    suspend fun createResep(
//        @Header("Cookie") token:String,
//        @Body data: RequestBody,
//    ):retrofit2.Response<CreateResep>

    @POST("/resep")
    suspend fun createResep(
        @Header("Cookie") token:String,
        @Body data: ForMakingRecipe,
    ):retrofit2.Response<CreateResep>

    @POST("/listbahan")
    suspend fun createListBahan(
        @Header("Cookie") token:String,
        @Body data: ForUploadToListBahan,
    ):retrofit2.Response<status>


    @GET("/savedrecipe/{id}")
    suspend fun getUserSavedRecipe(
        @Path("id") id: Int,
        @Header("Cookie") token:String
    ): retrofit2.Response<SavedRecipePerUser>

    @GET("/savedrecipe/{id}")
    suspend fun getsavedbyuser(
        @Path("id") id: Int,
        @Header("Cookie") token:String
    ): retrofit2.Response<SavedRecipePerUser>

    @POST("/resep/bahan/")
    suspend fun getResepFromBahan(
        @Header("Cookie") token:String,
        @Body data: ForUploadToFindingResepWithBahan,
    ):retrofit2.Response<ResepFromBahanSearch>

//    @POST("/users")
//    suspend fun postUserData(
//        @Body user: User
//    ): retrofit2.Response<JsonObject>

}