package com.example.edgefitness.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.edgefitness.Utils.SessionManager
import com.example.edgefitness.models.response.ExcericesResponse
import com.example.edgefitness.models.response.loginResult
import com.example.edgefitness.repository.UserRepository
import com.example.edgefitness.retrofit.BaseResponse
import kotlinx.coroutines.launch

class ExerciesViewMode(application: Application):AndroidViewModel(application) {

    val userRepo = UserRepository()
    val exerciesResult : MutableLiveData<BaseResponse<ExcericesResponse>> = MutableLiveData()


    fun getExercises(context : Context){
        exerciesResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = userRepo.getExercise(context,"${SessionManager.getToken(context)}")
                if (response?.code() == 200) {
                    exerciesResult.value = BaseResponse.Success(response.body())
                } else {
                    exerciesResult.value = BaseResponse.Error(response?.message())

                }
            }catch (ex : Exception){
                Log.e("Message",ex.message.toString())
                exerciesResult.value = BaseResponse.Error(ex.message)
            }
        }

    }

}