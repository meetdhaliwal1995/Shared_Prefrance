package com.codility.sharedpreferences.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Govind on 3/7/2018.
 */
class PrefManager(context: Context) {
    private var pref: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    init {
        pref = context.getSharedPreferences("Pref-Manager", Context.MODE_PRIVATE)
        editor = pref!!.edit()
    }

    fun storeValues(email: String, pwd: String, mobile: String) {
        editor!!.putString("email", email)
        editor!!.putString("pwd", pwd)
        editor!!.putString("mobile", mobile)
        editor!!.commit()
    }

    fun retrieveValues(str: String): String {
        return pref!!.getString(str, "")
    }

    fun clearPreferences() {
        editor!!.clear().commit();
    }
}