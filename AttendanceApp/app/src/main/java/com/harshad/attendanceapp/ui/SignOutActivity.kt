package com.harshad.attendanceapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.harshad.attendanceapp.R
import com.harshad.attendanceapp.databinding.ActivitySignOutBinding

class SignOutActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySignOutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_out)
        setIntentData()
        initClicks()
    }

    private fun initClicks() {
        binding.btnSignIn.setOnClickListener(this)
        binding.btnViewReport.setOnClickListener(this)
    }

    private fun setIntentData() {
        val loginTime = intent.getStringExtra("log_out_time")
        val loginDate = intent.getStringExtra("log_out_date")
        binding.tvDate.text = loginDate
        binding.tvTime.text = loginTime
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_sign_in -> {
                gotoActivity(MainActivity())
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
}