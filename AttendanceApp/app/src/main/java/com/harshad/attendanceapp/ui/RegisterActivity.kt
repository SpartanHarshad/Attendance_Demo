package com.harshad.attendanceapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.harshad.attendanceapp.R
import com.harshad.attendanceapp.databinding.ActivityRegisterBinding
import com.harshad.attendanceapp.models.User
import java.util.HashMap

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegisterBinding
    private val database = FirebaseDatabase.getInstance()
    private val dbRef = database.getReference("Users")
    private lateinit var auth: FirebaseAuth
    private val TAG = "RegisterActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        //Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        initClicks()
    }

    private fun initClicks() {
        binding.btnLogIn.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_log_in -> {
                gotoLoginScreen()
            }
            R.id.btn_register -> {
                registerUser()
            }
        }
    }

    private fun gotoLoginScreen() {
        val newActivity = Intent(this, MainActivity::class.java)
        startActivity(newActivity)
    }

    private fun registerUser() {
        if (validateInputData()) {
            val userName = binding.etvUserName.text.toString()
            val pwd = binding.etvPassword.text.toString()
            val emailId = binding.etvEmailId.text.toString()
            val phoneNo = binding.etvPhoneNo.text.toString()
            val user = User(userName, pwd, emailId, phoneNo)
            val newUserMap = hashMapOf<String, User>()
            newUserMap["NewUser"] = user
            saveUserData(newUserMap, user)
        }
    }

    private fun saveUserData(newUserMap: HashMap<String, User>, user: User) {
        val usersRef: DatabaseReference = dbRef //ref.child("Users")
        usersRef.child(user.userName).setValue(newUserMap).addOnCompleteListener(this) { task ->
            if (task.isComplete) {
                addUserDetailsToAuth(user)
            } else {
                showToast("Some thing went wrong")
            }
        }
    }

    private fun addUserDetailsToAuth(user: User) {
        auth.createUserWithEmailAndPassword(user.emailId, user.pwd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    showToast("Register Successfully...")
                    gotoLoginScreen()
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    showToast("please check your internet connection")
                }
            }
    }

    private fun validateInputData(): Boolean {
        val userName = binding.etvUserName.text.toString()
        val pwd = binding.etvPassword.text.toString()
        val emailId = binding.etvEmailId.text.toString()
        val phoneNo = binding.etvPhoneNo.text.toString()
        return if (userName.isEmpty() || userName.isBlank()) {
            showToast("please enter user name")
            false
        } else if (pwd.isEmpty() || pwd.isBlank()) {
            showToast("please enter password")
            false
        } else if (emailId.isEmpty() || emailId.isBlank()) {
            showToast("please enter email id")
            false
        } else if (phoneNo.isEmpty() || phoneNo.isBlank()) {
            showToast("please enter phone no")
            false
        } else {
            true
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
    }
}