package com.example.edgefitness.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.edgefitness.R
import com.example.edgefitness.Utils.ReuseFunction.processError
import com.example.edgefitness.Utils.SessionManager
import com.example.edgefitness.adaptors.ExercisesAdapter
import com.example.edgefitness.databinding.ActivityLoginBinding
import com.example.edgefitness.databinding.ActivityMainBinding
import com.example.edgefitness.models.response.exerciesResult
import com.example.edgefitness.retrofit.BaseResponse
import com.example.edgefitness.viewmodel.ExerciesViewMode
import com.example.edgefitness.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {
    lateinit var context: Context

    private val viewModel by viewModels<ExerciesViewMode>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var exercisesAdapter: ExercisesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.getExercises(this)
        setRecyclerView()
        getResult()


    }


    fun setRecyclerView() {
        binding.recyclerViewId.layoutManager = LinearLayoutManager(binding.root.context)
        exercisesAdapter = ExercisesAdapter(emptyList())
        binding.recyclerViewId.adapter = exercisesAdapter
    }

    fun getResult(){
        viewModel.exerciesResult.observe(this){
            when(it){
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    it.data?.data.let { exerciesResults ->
                        exercisesAdapter.updateData(exerciesResults ?: emptyList())
                    }
                    Log.e("Data",it.data.toString())
                }

                is BaseResponse.Error -> {
                    Log.e("Error",it.msg.toString())
                    processError(it.msg,applicationContext)
                }
                else -> {
                    stopLoading()
                }
            }
        }
    }




    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
    }


}
