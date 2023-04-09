package com.harshad.attendanceapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.harshad.attendanceapp.R
import com.harshad.attendanceapp.databinding.ActivityAttendanceBinding
import com.harshad.attendanceapp.viewmodel.AttendanceViewModel
import com.harshad.attendanceapp.viewmodel.AttendanceViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar

class SignInActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityAttendanceBinding
    val auth = FirebaseAuth.getInstance()
    lateinit var attendanceViewModel: AttendanceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_attendance)
        setIntentData()
        initData()
        initClicks()
    }

    private fun setIntentData() {
        val loginTime = intent.getStringExtra("log_in_time")
        val loginDate = intent.getStringExtra("log_in_date")
        binding.tvDate.text = loginDate
        binding.tvTime.text = loginTime
    }

    private fun initData() {
        val attendanceApplication = application as AttendanceApplication
        val attendanceRepo = attendanceApplication.attendanceRepo
        val factory = AttendanceViewModelFactory(attendanceRepo)
        attendanceViewModel = ViewModelProvider(this, factory)[AttendanceViewModel::class.java]
    }


    private fun initClicks() {
        binding.btnSignOut.setOnClickListener(this)
        binding.btnViewReport.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_sign_out -> {
                logOutUser()
            }
            R.id.btn_view_report -> {
                gotoActivity(AttendanceListActivity())
            }
        }
    }

    private fun gotoActivity(activity: Activity) {
        val desireScreen = Intent(this, activity::class.java)
        startActivity(desireScreen)
    }

    private fun logOutUser() {
        val time = Calendar.getInstance().time
        val sdf = SimpleDateFormat("h:mm a")
        val formattedTime = sdf.format(time)
        attendanceViewModel.signOutViewModel(formattedTime)
        auth.signOut()
        gotoActivity(MainActivity())
    }
}