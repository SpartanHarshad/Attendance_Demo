package com.harshad.attendanceapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.harshad.attendanceapp.R
import com.harshad.attendanceapp.adapter.AttendanceAdapter
import com.harshad.attendanceapp.databinding.ActivityShowAttendanceBinding
import com.harshad.attendanceapp.localdata.ReportEntity
import com.harshad.attendanceapp.viewmodel.AttendanceViewModel
import com.harshad.attendanceapp.viewmodel.AttendanceViewModelFactory

class AttendanceListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowAttendanceBinding
    lateinit var attendanceViewModel: AttendanceViewModel
    private var reports = mutableListOf<ReportEntity>()
    private lateinit var attAdapter: AttendanceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_attendance)
        initViewModel()
        setRecyclerview()
        getIntentData()
    }

    private fun getIntentData() {
        val email = intent.getStringExtra("email")
        if (email != null) {
            showReportByEmail(email)
        } else {
            showReportByEmail(MainActivity.userEmail)
        }
    }

    private fun showReportByEmail(email: String) {
        reports.clear()
        attendanceViewModel.getReportByEmailId(email).observe(this) {
            reports.addAll(it)
            attAdapter.notifyDataSetChanged()
        }
    }

    private fun initViewModel() {
        val attendanceApplication = application as AttendanceApplication
        val attendanceRepo = attendanceApplication.attendanceRepo
        val factory = AttendanceViewModelFactory(attendanceRepo)
        attendanceViewModel = ViewModelProvider(this, factory)[AttendanceViewModel::class.java]
    }

    private fun setRecyclerview() {
        attAdapter = AttendanceAdapter(reports)
        binding.rvAttendance.layoutManager = LinearLayoutManager(this)
        binding.rvAttendance.adapter = attAdapter
    }

}