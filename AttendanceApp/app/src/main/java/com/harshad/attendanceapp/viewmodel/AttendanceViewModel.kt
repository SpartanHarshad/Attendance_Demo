package com.harshad.attendanceapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.harshad.attendanceapp.localdata.ReportEntity
import com.harshad.attendanceapp.repository.AttendanceRepo

class AttendanceViewModel(val attendanceRepo: AttendanceRepo) : ViewModel() {

    fun signInViewModel(reportEntity: ReportEntity) {
        attendanceRepo.signInRepo(reportEntity)
    }

    fun signOutViewModel(time: String) {
        attendanceRepo.signOutRepo(time)
    }

    fun getReportByEmailId(email:String):LiveData<List<ReportEntity>>{
        return attendanceRepo.getReportByEmail(email)
    }
}