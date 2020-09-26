package com.felippe_neves.guests.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.felippe_neves.guests.service.constants.Constants
import com.felippe_neves.guests.service.model.GuestModel
import com.felippe_neves.guests.service.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application)
{
    private val mGuestRepository = GuestRepository.getInstance(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(filter: Int = Constants.FILTER.EMPTY)
    {
        when(filter)
        {
            Constants.FILTER.ABSENT -> {
                mGuestList.value = mGuestRepository.getAbsent()
            }
            Constants.FILTER.PRESENT -> {
                mGuestList.value = mGuestRepository.getPresent()
            }
            else -> {
                mGuestList.value = mGuestRepository.getAll()
            }
        }

    }

    fun delete(id: Int)
    {
        mGuestRepository.delete(id)
    }
}