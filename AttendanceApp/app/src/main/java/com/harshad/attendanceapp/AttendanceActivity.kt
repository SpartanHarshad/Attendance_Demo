package com.harshad.attendanceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.harshad.attendanceapp.databinding.ActivityAttendanceBinding

class AttendanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAttendanceBinding
    private var isUserLoggedIn: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_attendance)
        isUserLoggedIn = intent.getStringExtra("currentUser")
        Toast.makeText(this, "current user data $isUserLoggedIn", Toast.LENGTH_SHORT).show()
    }
}