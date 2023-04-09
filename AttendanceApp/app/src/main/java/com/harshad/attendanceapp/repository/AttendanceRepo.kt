package com.harshad.attendanceapp.repository


import androidx.lifecycle.LiveData
import com.harshad.attendanceapp.localdata.AttendanceReportDao
import com.harshad.attendanceapp.localdata.ReportEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AttendanceRepo(val attendanceReportDao: AttendanceReportDao) {

    fun signInRepo(reportEntity: ReportEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            attendanceReportDao.signInUser(reportEntity)
        }
    }

    fun signOutRepo(time: String) {
        CoroutineScope(Dispatchers.IO).launch {
            attendanceReportDao.signOutUser(time)
        }
    }

    fun getAttendanceReport(): LiveData<List<ReportEntity>> {
        return attendanceReportDao.getAttendanceReport()
    }

}