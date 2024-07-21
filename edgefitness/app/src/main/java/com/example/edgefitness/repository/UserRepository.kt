package com.example.edgefitness.repository

import android.content.Context
import com.example.edgefitness.Utils.SessionManager
import com.example.edgefitness.models.response.ExcericesResponse
import com.example.edgefitness.models.response.LoginResponse
import com.example.mvvmkotlinexample.retrofit.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {

    suspend fun loginUser(email : String,password : String,context : Context): Response<LoginResponse>? {
        return  withContext(Dispatchers.IO) {
            ApiInterface.getApi(context)?.getLogin(email, password)
        }
    }

    suspend fun getExercise(context : Context,token : String):Response<ExcericesResponse>?{
        return withContext(Dispatchers.IO){
            ApiInterface.getApi(context)?.getExercise(token)
        }
    }
}