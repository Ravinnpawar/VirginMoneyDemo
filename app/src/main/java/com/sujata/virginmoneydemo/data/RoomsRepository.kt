package com.sujata.virginmoneydemo.data

class RoomsRepository(val roomsDataSource: RoomsDataSource) {
    suspend fun getRoomsData()=roomsDataSource.getRoomsData()
}