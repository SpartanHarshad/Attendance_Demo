package com.harshad.attendanceapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.harshad.attendanceapp.R
import com.harshad.attendanceapp.databinding.ActivityAdminBinding
import com.harshad.attendanceapp.models.User
import com.harshad.attendanceapp.models.UserModel
import kotlinx.coroutines.NonCancellable.children

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    private val database = FirebaseDatabase.getInstance().reference
    private val userList = database.child("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin)
        getUserList()
    }

    private fun getUserList() {
        val query: Query = userList.orderByChild("key")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val newUserList = mutableListOf<UserModel?>()
                for (users in snapshot.children) {
                    for (user in users.children) {
                        val newUser = user.getValue(UserModel::class.java)
                        newUserList.add(newUser)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                //
            }

        }
        query.addListenerForSingleValueEvent(eventListener)
    }
}