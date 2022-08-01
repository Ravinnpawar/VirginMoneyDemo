package com.sujata.virginmoneydemo.data

import com.sujata.virginmoneydemo.domain.PeoplesData
import com.sujata.virginmoneydemo.framework.api.dto.Peoples
import com.sujata.virginmoneydemo.framework.api.dto.PeoplesItem

interface PeopleDataSource {
    suspend fun getPeoplesData(): List<PeoplesData>
}