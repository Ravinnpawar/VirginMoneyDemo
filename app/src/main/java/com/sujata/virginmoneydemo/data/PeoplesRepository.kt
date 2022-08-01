package com.sujata.virginmoneydemo.data

class PeoplesRepository(val peopleDataSource: PeopleDataSource) {
    suspend fun getPeoplesData()=peopleDataSource.getPeoplesData()
}