package com.sujata.virginmoneydemo.framework.api

import com.sujata.virginmoneydemo.domain.PeoplesData
import com.sujata.virginmoneydemo.framework.api.dto.Peoples
import com.sujata.virginmoneydemo.framework.api.dto.Rooms
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface PeoplesApiService {
    companion object{
        operator fun invoke(): PeoplesApiService {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://61e947967bc0550017bc61bf.mockapi.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PeoplesApiService::class.java)
        }
    }
    @GET("people")
    suspend fun getPeoples(): Response<Peoples>

    @GET("rooms")
    suspend fun getRooms():Response<Rooms>
}