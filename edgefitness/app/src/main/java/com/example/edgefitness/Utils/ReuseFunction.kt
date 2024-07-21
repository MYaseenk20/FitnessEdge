package com.example.edgefitness.Utils

import android.content.Context
import android.widget.Toast

object ReuseFunction {


    fun processError(msg: String?,context: Context) {
        showToast("Error:" + msg,context)
    }

    fun showToast(msg: String,context:Context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}