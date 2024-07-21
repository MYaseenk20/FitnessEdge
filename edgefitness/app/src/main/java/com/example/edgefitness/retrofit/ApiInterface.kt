package com.example.mvvmkotlinexample.retrofit

import android.content.Context
import com.example.edgefitness.models.response.ExcericesResponse
import com.example.edgefitness.models.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {

    @POST("login")
    @FormUrlEncoded
    suspend fun getLogin(@Field("email") email: String, @Field("password") password: String) : Response<LoginResponse>

    @GET("exercises")
    suspend fun getExercise(@Header("token") bearerToken : String) : Response<ExcericesResponse>


    companion object {
        fun getApi(context : Context): ApiInterface? {
            return RetrofitClient(context).client?.create(ApiInterface::class.java)
        }
    }

}