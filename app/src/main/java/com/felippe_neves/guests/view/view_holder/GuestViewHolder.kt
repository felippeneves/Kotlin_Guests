package com.felippe_neves.guests.view.view_holder

import android.app.AlertDialog
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.felippe_neves.guests.R
import com.felippe_neves.guests.service.model.GuestModel
import com.felippe_neves.guests.view.listener.GuestListener

class GuestViewHolder(itemView: View, private val listener: GuestListener) : RecyclerView.ViewHolder(itemView)
{
    fun bind(guest: GuestModel)
    {
        val tvName = itemView.findViewById<AppCompatTextView>(R.id.tv_name)
        tvName.text = guest.name

        tvName.setOnClickListener {
            listener.onClick(guest.id)
        }

        tvName.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.remove_guest)
                .setMessage(R.string.want_remove)
                .setPositiveButton(R.string.remove) { dialog, witch ->
                    listener.onDelete(guest.id)
                }
                .setNeutralButton(R.string.cancel, null)
                .show()

            true
        }
    }
}