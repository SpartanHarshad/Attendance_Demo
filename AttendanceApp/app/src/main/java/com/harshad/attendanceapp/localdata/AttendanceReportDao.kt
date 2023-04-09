package com.harshad.attendanceapp.localdata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AttendanceReportDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun signInUser(reportEntity: ReportEntity)

    @Query("UPDATE Attendance SET sign_Out = :time")
    suspend fun signOutUser(time: String)

    @Query("SELECT * FROM 'Attendance'")
    fun getAttendanceReport(): List<ReportEntity>
}