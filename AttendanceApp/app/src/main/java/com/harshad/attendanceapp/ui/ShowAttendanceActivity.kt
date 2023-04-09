package com.harshad.attendanceapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.harshad.attendanceapp.R
import com.harshad.attendanceapp.databinding.ActivityShowAttendanceBinding

class ShowAttendanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowAttendanceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_show_attendance)

    }
}