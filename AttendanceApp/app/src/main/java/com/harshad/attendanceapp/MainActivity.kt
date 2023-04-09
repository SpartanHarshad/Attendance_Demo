package com.harshad.attendanceapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.harshad.attendanceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        initClicks()
    }

    private fun initClicks() {
        binding.btnLogIn.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_log_in -> {
                logInUser()
            }
            R.id.btn_register -> {
                gotoDesireScreen(RegisterActivity(), auth.currentUser)
            }
        }
    }

    private fun logInUser() {
        val email = binding.etvEmail.text.toString()
        val pwd = binding.etvPassword.text.toString()
        if (email.isNotEmpty() && pwd.isNotEmpty()) {
            logInGotoAttendanceScreen(email, pwd)
        } else if (pwd.isEmpty() || pwd.isBlank()) {
            showToast("Please enter password")
        } else {
            showToast("Please enter email")
        }
    }

    private fun logInGotoAttendanceScreen(email: String, pwd: String) {
        auth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success")
                gotoDesireScreen(AttendanceActivity(), auth.currentUser)
            } else {
                // sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                showToast("Authentication failed.")
            }
        }
    }

    private fun gotoDesireScreen(desireActivity: Activity, currentUser: FirebaseUser?) {
        val newActivity = Intent(this, desireActivity::class.java)
        newActivity.putExtra("currentUser", currentUser)
        startActivity(newActivity)
    }

    private fun showToast(msg: String) {
        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
    }
}