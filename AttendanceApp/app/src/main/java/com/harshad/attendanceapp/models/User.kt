package com.harshad.attendanceapp.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var userName:String,
    var pwd:String,
    var emailId:String,
    var phoneNo:String
)
