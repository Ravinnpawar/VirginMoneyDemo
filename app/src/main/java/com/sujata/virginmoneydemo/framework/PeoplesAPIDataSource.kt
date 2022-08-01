package com.sujata.virginmoneydemo.framework

import android.util.Log
import com.sujata.virginmoneydemo.data.PeopleDataSource
import com.sujata.virginmoneydemo.domain.PeoplesData
import com.sujata.virginmoneydemo.framework.api.PeoplesApiService

const val TAG = "PeoplesAPIDataSource"

class PeoplesAPIDataSource(private val peoplesApiService: PeoplesApiService) : PeopleDataSource {
    override suspend fun getPeoplesData(): List<PeoplesData> {
        val peoplesResponse = peoplesApiService.getPeoples()
        val peoplesData = mutableListOf<PeoplesData>()
        if (peoplesResponse.isSuccessful) {
            peoplesResponse.body()?.run {
                Log.i(TAG, "Size " + this.size)
                this.forEach {
                    peoplesData.add(PeoplesData(
                            it.avatar,
                            it.firstName,
                            it.jobtitle,
                            it.lastName

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
        return peoplesData
    }

}