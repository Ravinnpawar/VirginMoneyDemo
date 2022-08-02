package com.sujata.virginmoneydemo.domain

import com.sujata.virginmoneydemo.framework.api.dto.Data

data class PeoplesData(
    val avatar: String,
    val firstName: String,
    val jobtitle: String,
    val lastName: String,
    val email: String,
    val favouriteColor: String
  /*  val email: String,
    val favouriteColor: String,
    val firstName: String,
    val id: String,
    val jobtitle: String,
    val lastName: String,
    val to: String*/
) {
}