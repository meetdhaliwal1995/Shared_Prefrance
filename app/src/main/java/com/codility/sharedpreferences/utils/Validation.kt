package com.codility.sharedpreferences.utils

/**
 * Created by Govind on 3/7/2018.
 */
object Validation {

    fun isValidMail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(pass: String?): Boolean {
        return pass != null && pass.length > 6
    }

    fun isValidMobileNumber(mobileNo: String): Boolean {
        return if (mobileNo.length != 10) {
            false
        } else {
            android.util.Patterns.PHONE.matcher(mobileNo).matches()
        }
    }
}