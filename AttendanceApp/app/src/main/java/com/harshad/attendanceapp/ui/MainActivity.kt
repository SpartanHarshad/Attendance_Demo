package com.harshad.attendanceapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.harshad.attendanceapp.R
import com.harshad.attendanceapp.databinding.ActivityMainBinding
import com.harshad.attendanceapp.localdata.ReportEntity
import com.harshad.attendanceapp.util.Util
import com.harshad.attendanceapp.viewmodel.AttendanceViewModel
import com.harshad.attendanceapp.viewmodel.AttendanceViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private val TAG = "MainActivity"
    private lateinit var attendanceViewModel: AttendanceViewModel

    companion object {
        var userEmail = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        initViewModel()
        initClicks()
    }

    private fun initViewModel() {
        val attendanceApplication = application as AttendanceApplication
        val attendanceRepo = attendanceApplication.attendanceRepo
        val factory = AttendanceViewModelFactory(attendanceRepo)
        attendanceViewModel = ViewModelProvider(this, factory)[AttendanceViewModel::class.java]
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
                val regi = Intent(this, RegisterActivity::class.java)
                startActivity(regi)
            }
        }
    }

    private fun logInUser() {
        val email = binding.etvEmail.text.toString()
        val pwd = binding.etvPassword.text.toString()
        userEmail = email
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
                if (email == Util.adminEmail) {
                    val admin = Intent(this, AdminActivity::class.java)
                    startActivity(admin)
                } else {
                    localSystemLogIn(email)
                }
            } else {
                // sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                showToast("Authentication failed.")
            }
        }
    }

    private fun localSystemLogIn(email: String) {
        val dateTime = Calendar.getInstance().time
        val logInTime = Util.getFormattedTime(dateTime)
        val loginDate = Util.getFormattedDate(dateTime)
        val reportEntity = ReportEntity(loginDate, logInTime, "", email)
        attendanceViewModel.signInViewModel(reportEntity)
        gotoSignInOutScreen(SignInActivity(), logInTime, loginDate)
    }

    private fun gotoSignInOutScreen(
        desireActivity: SignInActivity,
        logInTime: String,
        loginDate: String
    ) {
        val newActivity = Intent(this, desireActivity::class.java)
        newActivity.putExtra("log_in_time", logInTime)
        newActivity.putExtra("log_in_date", loginDate)
        startActivity(newActivity)
    }

    private fun showToast(msg: String) {
        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
    }
}