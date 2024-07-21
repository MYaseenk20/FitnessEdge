package com.example.edgefitness.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edgefitness.models.request.LoginRequest
import com.example.edgefitness.models.response.LoginResponse
import com.example.edgefitness.repository.UserRepository
import com.example.edgefitness.retrofit.BaseResponse
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val userRepo = UserRepository()
    val loginResult: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()


    fun loginUser(email: String, pwd: String,context : Context) {

        loginResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val response = userRepo.loginUser(email = email, password = pwd,context = context)
                if (response?.code() == 200) {
                    loginResult.value = BaseResponse.Success(response.body())
                } else {
                    loginResult.value = BaseResponse.Error(response?.message())
                }

            } catch (ex: Exception) {
                Log.e("Message",ex.message.toString())
                loginResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

}