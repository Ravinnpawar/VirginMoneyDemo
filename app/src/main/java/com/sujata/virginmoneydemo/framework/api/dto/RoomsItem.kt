package com.sujata.virginmoneydemo.framework.api.dto

data class RoomsItem(
    val createdAt: String,
    val id: String,
    val isOccupied: Boolean,
    val maxOccupancy: Int
)