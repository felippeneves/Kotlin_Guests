package com.felippe_neves.guests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.felippe_neves.guests.R
import com.felippe_neves.guests.service.constants.Constants
import com.felippe_neves.guests.view.adapter.GuestAdapter
import com.felippe_neves.guests.view.listener.GuestListener
import com.felippe_neves.guests.view_model.GuestsViewModel

class PresentFragment : Fragment()
{
    private lateinit var mViewModel: GuestsViewModel
    private val mAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_present, container, false)

        val rvPresentsGuests = root.findViewById<RecyclerView>(R.id.rv_presents_guests)

        rvPresentsGuests.layoutManager = LinearLayoutManager(context)

        rvPresentsGuests.adapter = mAdapter

        mListener = object : GuestListener
        {
            override fun onClick(id: Int)
            {
                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(Constants.GUEST_ID, id)

                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int)
            {
                mViewModel.delete(id)
                mViewModel.load(Constants.FILTER.PRESENT)
            }
        }

        mAdapter.attachListener(mListener)
        observer()

        return root
    }

    override fun onResume()
    {
        super.onResume()
        mViewModel.load(Constants.FILTER.PRESENT)
    }

    private fun observer()
    {
        //viewLifecycleOwner é a mesma variavel do fragment que faz o que o context faz na activity
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
}