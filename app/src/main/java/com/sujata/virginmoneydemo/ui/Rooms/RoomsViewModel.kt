package com.sujata.virginmoneydemo.ui.Rooms

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sujata.virginmoneydemo.data.RoomsRepository
import com.sujata.virginmoneydemo.domain.PeoplesData
import com.sujata.virginmoneydemo.domain.RoomsData
import com.sujata.virginmoneydemo.framework.api.Resource
import com.sujata.virginmoneydemo.framework.api.dto.Rooms
import com.sujata.virginmoneydemo.ui.peoples.TAG
import kotlinx.coroutines.launch

class RoomsViewModel(private val roomsRepository: RoomsRepository) : ViewModel() {

    private val _roomsDataResponse =MutableLiveData<Resource<List<RoomsData>>>()
    val roomsDataResponse:LiveData<Resource<List<RoomsData>>> get() = _roomsDataResponse

    fun fetchRoomsData(){
        _roomsDataResponse.postValue(Resource.loading(null))
        viewModelScope.launch {
            val roomsData=roomsRepository.getRoomsData()
            Log.i(TAG,"Size"+roomsData.size)
            if (roomsData.isNotEmpty()){
                Log.i(TAG,"Size in VM"+roomsData.size)
                _roomsDataResponse.postValue(Resource.success(roomsData))
            }else{
                _roomsDataResponse.postValue(Resource.error(null,"Something went wrong"))
            }
        }
    }
}