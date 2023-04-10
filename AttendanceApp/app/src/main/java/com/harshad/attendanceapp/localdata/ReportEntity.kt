package com.harshad.attendanceapp.localdata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Attendance")
data class ReportEntity(
    @ColumnInfo(name = "Date")
    var date: String,
    @ColumnInfo(name = "sign_In")
    var signInTime: String,
    @ColumnInfo(name = "sign_Out")
    var signOutTime: String,
    @ColumnInfo(name = "email")
    var email: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Rid")
    var rId: Int? = null
}