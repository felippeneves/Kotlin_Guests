package com.felippe_neves.guests.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.felippe_neves.guests.view_model.GuestFormViewModel
import com.felippe_neves.guests.R
import com.felippe_neves.guests.service.constants.Constants
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener
{
    private val mContext = this
    private lateinit var mViewModel : GuestFormViewModel
    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(mContext).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
        loadData()
        //
        et_name.requestFocus()
    }

    override fun onClick(view: View)
    {
        when(view.id)
        {
            R.id.bt_save ->
            {
                val name = et_name.text.toString()
                val presence = rb_presence.isChecked
                
                mViewModel.save(mGuestId, name, presence)
            }
        }
    }

    private fun loadData()
    {
        val bundle = intent.extras
        if(bundle != null)
        {
            mGuestId = bundle.getInt(Constants.GUEST_ID)
            mViewModel.load(mGuestId)
        }
    }

    private fun setListeners()
    {
        bt_save.setOnClickListener(this)
    }

    private fun observe()
    {
        mViewModel.saveGuest.observe(this, Observer {
            if(it)
            {
                Toast.makeText(mContext, getString(R.string.sucess), Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(mContext, getString(R.string.failure), Toast.LENGTH_SHORT).show()
            }

            finish()
        })

        mViewModel.guest.observe(this, {
            et_name.setText(it.name)
            if(it.presence)
                rb_presence.isChecked = true
            else
                rb_absent.isChecked = true
        })
    }
}