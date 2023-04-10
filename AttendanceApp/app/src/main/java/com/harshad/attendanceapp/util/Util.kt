package com.harshad.attendanceapp.util

import java.text.SimpleDateFormat
import java.util.*

object Util {

    const val adminEmail = "admin@gmail.com"
    const val adminPwd = "Test123"

    fun getFormattedTime(time: Date): String {
        val sdf = SimpleDateFormat("h:mm a")
        return sdf.format(time)
    }

    fun getFormattedDate(time: Date): String {
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        return sdf.format(time)
    }

}