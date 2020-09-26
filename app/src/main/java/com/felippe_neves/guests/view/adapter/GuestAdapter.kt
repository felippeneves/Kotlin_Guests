package com.felippe_neves.guests.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.felippe_neves.guests.R
import com.felippe_neves.guests.service.model.GuestModel
import com.felippe_neves.guests.view.listener.GuestListener
import com.felippe_neves.guests.view.view_holder.GuestViewHolder

class GuestAdapter : RecyclerView.Adapter<GuestViewHolder>()
{
    private var mGuestList: List<GuestModel> = arrayListOf()
    private lateinit var mListener: GuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder
    {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest, parent, false)
        return GuestViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int)
    {
        holder.bind(mGuestList[position])
    }

    override fun getItemCount(): Int
    {
        return mGuestList.count()
    }

    fun updateGuests(list: List<GuestModel>)
    {
        mGuestList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: GuestListener)
    {
        mListener = listener
    }
}