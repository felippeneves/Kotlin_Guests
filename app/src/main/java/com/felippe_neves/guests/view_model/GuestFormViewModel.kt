package com.felippe_neves.guests.view_model

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.felippe_neves.guests.service.model.GuestModel
import com.felippe_neves.guests.service.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application)
{
    private val mContext = application.applicationContext
    private val mGuestRepository = GuestRepository.getInstance(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>()
    var saveGuest: LiveData<Boolean> = mSaveGuest

    private var mGuest = MutableLiveData<GuestModel>()
    var guest: LiveData<GuestModel> = mGuest

    fun save(id: Int, name: String, presence: Boolean)
    {
        val guest = GuestModel(id, name, presence)

        if(id == 0)
            mSaveGuest.value = mGuestRepository.save(guest)
        else
            mSaveGuest.value = mGuestRepository.update(guest)
    }

    fun load(id: Int)
    {
        mGuest.value = mGuestRepository.get(id)
    }
}