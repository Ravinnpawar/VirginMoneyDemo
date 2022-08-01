package com.sujata.virginmoneydemo.framework

import android.util.Log
import com.sujata.virginmoneydemo.data.RoomsDataSource
import com.sujata.virginmoneydemo.domain.RoomsData
import com.sujata.virginmoneydemo.framework.api.PeoplesApiService

class RoomsAPIDataSource(private val peoplesApiService: PeoplesApiService):RoomsDataSource {
    override suspend fun getRoomsData(): List<RoomsData> {
        val roomsResponse=peoplesApiService.getRooms()
        val roomsData= mutableListOf<RoomsData>()
        if (roomsResponse.isSuccessful) {
            roomsResponse.body()?.run {
                Log.i(TAG, "Size " + this.size)
                this.forEach {
                    roomsData.add(
                        RoomsData(
                        it.createdAt,
                        it.id,
                        it.isOccupied,
                        it.maxOccupancy

                        /*it.email,
                        it.favouriteColor,
                        it.id,
                        it.to*/
                    )
                    )
                }
            }

        } else {
            System.out.println(TAG + " : Response issue")
        }
        return roomsData
    }
}