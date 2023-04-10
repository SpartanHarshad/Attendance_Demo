package com.harshad.attendanceapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.harshad.attendanceapp.R
import com.harshad.attendanceapp.adapter.OnItemClick
import com.harshad.attendanceapp.adapter.UserDetailsAdapter
import com.harshad.attendanceapp.databinding.ActivityAdminBinding
import com.harshad.attendanceapp.models.User
import com.harshad.attendanceapp.models.UserModel
import kotlinx.coroutines.NonCancellable.children
import kotlinx.coroutines.NonCancellable.start

class AdminActivity : AppCompatActivity(), OnItemClick {

    private lateinit var binding: ActivityAdminBinding
    private val database = FirebaseDatabase.getInstance().reference
    private val userList = database.child("Users")
    val newUserList = mutableListOf<UserModel?>()
    private lateinit var userDAdapter: UserDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin)
        setRecyclerView()
        getUserList()
    }

    private fun getUserList() {
        val query: Query = userList.orderByChild("key")
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (users in snapshot.children) {
                    for (user in users.children) {
                        val newUser = user.getValue(UserModel::class.java)
                        newUserList.add(newUser)
                    }
                }
                userDAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                //
            }

        }
        query.addListenerForSingleValueEvent(eventListener)
    }

    private fun setRecyclerView() {
        userDAdapter = UserDetailsAdapter(newUserList, this)
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = userDAdapter
    }

    override fun onItemClick(userModel: UserModel?) {
        val reportScreen = Intent(this, AttendanceListActivity::class.java)
        reportScreen.putExtra("email", userModel?.emailId)
        startActivity(reportScreen)
    }
}