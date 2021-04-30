package com.codility.sharedpreferences

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.codility.sharedpreferences.utils.PrefManager
import com.codility.sharedpreferences.utils.Validation
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by Govind on 3/7/2018.
 */
class MainActivity : AppCompatActivity() {
    private var prefManager: PrefManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Initialise PrefManager
        prefManager = PrefManager(this)
        //Display data if not empty
        displayPreferenceData()
    }

    fun saveClickView(view: View) {
        if (isValidate()) {
            dismissKeyboard(this)
            prefManager!!.storeValues(email.text.toString(), password.text.toString(), mobile.text.toString())
            displayPreferenceData()
            showToast("Saved Successfully..!!")
        }
    }

    fun clearPreferenceView(view: View) {
        prefManager!!.clearPreferences()
        tvEmail.text = ""
        tvPwd.text = ""
        tvMobile.text = ""
    }

    private fun isValidate(): Boolean {
        val email = email.text.toString()
        val password = password.text.toString()
        val mobile = mobile.text.toString()

        if (email.isEmpty() || !Validation.isValidMail(email)) {
            showToast("Enter valid email Id..!!")
            return false
        }
        if (password.isEmpty() || !Validation.isValidPassword(password)) {
            showToast("Enter 7 digits valid password..!!")
            return false
        }
        if (mobile.isEmpty() || !Validation.isValidMobileNumber(mobile)) {
            showToast("Enter 10 digits valid mobile number..!!")
            return false
        }
        return true
    }

    @SuppressLint("SetTextI18n")
    private fun displayPreferenceData() {
        val email = prefManager!!.retrieveValues("email")
        val pwd = prefManager!!.retrieveValues("pwd")
        val mobile = prefManager!!.retrieveValues("mobile")

        if (!email.isEmpty()) {
            tvEmail.text = "Email : ".plus(email)
        }
        if (!pwd.isEmpty()) {
            tvPwd.text = "Password : ".plus(pwd)
        }
        if (!mobile.isEmpty()) {
            tvMobile.text = "Mobile : ".plus(mobile)
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun dismissKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (null != activity.currentFocus)
            imm.hideSoftInputFromWindow(activity.currentFocus!!.applicationWindowToken, 0)
    }
}