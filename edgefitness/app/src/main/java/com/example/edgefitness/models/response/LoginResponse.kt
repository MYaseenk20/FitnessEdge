package com.example.edgefitness.models.response

data class LoginResponse(
    val data: loginResult? = null,
    val message: String = "",
    val success: Boolean = false
)

data class loginResult(
    val bio_data: Any,
    val created_at: String,
    val email: String,
    val email_verified_at: String,
    val id: Int,
    val joined_at: Any,
    val name: String,
    val nic: Any,
    val phone: Any,
    val profile_pic: Any,
    val role: String,
    val token: String,
    val updated_at: String,
    val user_code: String
)