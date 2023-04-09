package com.harshad.attendanceapp.ui

import android.app.Application
import com.harshad.attendanceapp.localdata.AttendanceDb
import com.harshad.attendanceapp.repository.AttendanceRepo

class AttendanceApplication : Application() {
    val attendanceReportDao by lazy {
        val roomDatabase = AttendanceDb.getDatabaseInstance(this)
        roomDatabase.getAttendanceDao()
    }

    val attendanceRepo by lazy {
        AttendanceRepo(attendanceReportDao)
    }
}