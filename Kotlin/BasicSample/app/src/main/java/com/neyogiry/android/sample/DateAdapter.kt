package com.neyogiry.android.sample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_date.view.*

class DateAdapter(private var mDates: ArrayList<String>): RecyclerView.Adapter<DateAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_date, parent, false))
    }

    override fun getItemCount(): Int {
        return mDates.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dateTime = mDates?.get(position)
        holder.dateTime?.text = dateTime
    }

    fun updateList(list: ArrayList<String>) {
        this.mDates = list
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dateTime: TextView = itemView?.tv_date
    }

}