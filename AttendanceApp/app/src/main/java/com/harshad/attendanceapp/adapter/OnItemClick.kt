package com.harshad.attendanceapp.adapter

import com.harshad.attendanceapp.models.UserModel

interface OnItemClick {
    fun onItemClick(userModel: UserModel?)
}