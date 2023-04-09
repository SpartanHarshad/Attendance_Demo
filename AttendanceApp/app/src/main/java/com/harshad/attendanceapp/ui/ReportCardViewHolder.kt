package com.harshad.attendanceapp.ui

import androidx.recyclerview.widget.RecyclerView
import com.harshad.attendanceapp.databinding.ItemLayoutBinding
import com.harshad.attendanceapp.localdata.ReportEntity

class ReportCardViewHolder(private val itemLayoutBinding: ItemLayoutBinding) :
    RecyclerView.ViewHolder(itemLayoutBinding.root) {

    fun onBind(reportEntity: ReportEntity) {
        itemLayoutBinding.tvDate.text = reportEntity.date
        itemLayoutBinding.tvSignTimeValue.text = reportEntity.signInTime
        itemLayoutBinding.tvSignOutTimeValue.text = reportEntity.signOutTime
    }

}