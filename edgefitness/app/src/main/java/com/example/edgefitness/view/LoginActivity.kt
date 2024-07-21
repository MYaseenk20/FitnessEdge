package com.example.edgefitness.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.edgefitness.R
import com.example.edgefitness.Utils.SessionManager
import com.example.edgefitness.databinding.ActivityLoginBinding
import com.example.edgefitness.databinding.ActivityMainBinding
import com.example.edgefitness.models.response.LoginResponse
import com.example.edgefitness.retrofit.BaseResponse
import com.example.edgefitness.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val token = SessionManager.getToken(this)
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }

        viewModel.loginResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    processLogin(it.data)
                }

                is BaseResponse.Error -> {
                    Log.e("Error",it.msg.toString())
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }


        binding.btnLogin.setOnClickListener {
            doLogin()

        }





    }

    fun showLoading() {
        binding.prgbar.visibility= View.VISIBLE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
        finish()
    }


    fun processLogin(data: LoginResponse?) {
        showToast("Success:" + data?.message)
        if (!data?.data?.token.isNullOrEmpty()) {
            data?.data?.token?.let { SessionManager.saveAuthToken(this, it) }
            navigateToHome()
        }
    }

    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    fun doLogin() {

        val email = binding.txtInputEmail.text.toString()
        val pwd = binding.txtPass.text.toString()

        viewModel.loginUser(email = email, pwd = pwd,context = applicationContext)
    }
}