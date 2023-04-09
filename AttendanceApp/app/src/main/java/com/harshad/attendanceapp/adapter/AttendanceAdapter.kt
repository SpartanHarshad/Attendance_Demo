package com.harshad.attendanceapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.harshad.attendanceapp.R
import com.harshad.attendanceapp.databinding.ItemLayoutBinding
import com.harshad.attendanceapp.localdata.ReportEntity
import com.harshad.attendanceapp.ui.ReportCardViewHolder

class AttendanceAdapter(private val reports: List<ReportEntity>) :
    RecyclerView.Adapter<ReportCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportCardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemLayoutBinding: ItemLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_layout, parent, false)
        return ReportCardViewHolder(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: ReportCardViewHolder, position: Int) {
        val reportEntity = reports[position]
        holder.onBind(reportEntity)
    }

    override fun getItemCount(): Int {
        return reports.size
    }
}