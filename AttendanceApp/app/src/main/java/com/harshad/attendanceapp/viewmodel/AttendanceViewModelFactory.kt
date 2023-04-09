package com.harshad.attendanceapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harshad.attendanceapp.repository.AttendanceRepo

class AttendanceViewModelFactory(val attendanceRepo: AttendanceRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AttendanceViewModel(attendanceRepo) as T
    }
}