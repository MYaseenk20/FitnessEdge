package com.example.edgefitness.models.response

data class ExcericesResponse(
    val data: List<exerciesResult>,
    val message: String,
    val success: Boolean
)

data class exerciesResult(
    val created_at: String,
    val details: Any,
    val featured_image: Any,
    val id: Int,
    val images: List<Any>,
    val name: String,
    val updated_at: String
)