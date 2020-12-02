package com.example.dacfusion.services

import com.example.dacfusion.model.loginUser
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface loginUser {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email:String,
        @Field("password") password:String

    ):Call<loginUser>

}