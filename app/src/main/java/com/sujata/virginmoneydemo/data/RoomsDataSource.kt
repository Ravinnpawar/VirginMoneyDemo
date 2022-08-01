package com.sujata.virginmoneydemo.data

import com.sujata.virginmoneydemo.domain.RoomsData
import com.sujata.virginmoneydemo.framework.api.dto.RoomsItem

interface RoomsDataSource {
    suspend fun getRoomsData(): List<RoomsData>
}