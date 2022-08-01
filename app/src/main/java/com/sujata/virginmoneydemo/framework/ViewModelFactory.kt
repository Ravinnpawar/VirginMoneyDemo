package com.sujata.virginmoneydemo.framework

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sujata.virginmoneydemo.data.PeoplesRepository
import com.sujata.virginmoneydemo.data.RoomsRepository
import com.sujata.virginmoneydemo.framework.api.PeoplesApiService
import com.sujata.virginmoneydemo.ui.Rooms.RoomsViewModel
import com.sujata.virginmoneydemo.ui.peoples.PeoplesViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PeoplesViewModel::class.java)) {
            PeoplesViewModel(
                PeoplesRepository(PeoplesAPIDataSource(PeoplesApiService()))
            ) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
        return if (modelClass.isAssignableFrom(PeoplesViewModel::class.java)) {
            RoomsViewModel(
                RoomsRepository(RoomsAPIDataSource(PeoplesApiService()))
            ) as T
        }else{
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}