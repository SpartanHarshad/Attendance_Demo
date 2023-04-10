package com.harshad.attendanceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.harshad.attendanceapp.R
import com.harshad.attendanceapp.databinding.ItemUserDetailsLayoutBinding
import com.harshad.attendanceapp.models.UserModel

class UserDetailsAdapter(var users: List<UserModel?>, var listener: OnItemClick) :
    RecyclerView.Adapter<UserDetailsAdapter.UserDetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemUserDetailsLayout: ItemUserDetailsLayoutBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_user_details_layout, parent, false
        )
        return UserDetailsViewHolder(itemUserDetailsLayout, listener)
    }

    override fun onBindViewHolder(holder: UserDetailsViewHolder, position: Int) {
        holder.onBind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class UserDetailsViewHolder(
        val itemUserDetailsLayout: ItemUserDetailsLayoutBinding,
        val listener: OnItemClick
    ) :
        RecyclerView.ViewHolder(itemUserDetailsLayout.root) {

        fun onBind(userModel: UserModel?) {
            itemUserDetailsLayout.tvUserName.text = "User Name : ${userModel?.userName}"
            itemUserDetailsLayout.tvEmail.text = "Email Id : ${userModel?.emailId}"
            itemUserDetailsLayout.tvPhoneNo.text = "Phone no : ${userModel?.phoneNo}"
            itemUserDetailsLayout.clyUser.setOnClickListener {
                listener.onItemClick(userModel)
            }
        }
    }
}