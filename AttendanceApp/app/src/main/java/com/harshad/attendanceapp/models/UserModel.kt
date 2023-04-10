package com.harshad.attendanceapp.models

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserModel(
    var userName: String? = "",
    var pwd: String? = "",
    var emailId: String? = "",
    var phoneNo: String? = ""
) {

    @Exclude
    fun toMap(): Map<String, String?> {
        return mapOf(
            "userName" to userName,
            "pwd" to pwd,
            "emailId" to emailId,
            "phoneNo" to phoneNo
        )
    }
}