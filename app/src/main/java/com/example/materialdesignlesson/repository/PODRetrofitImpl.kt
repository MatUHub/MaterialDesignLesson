package com.example.materialdesignlesson.repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PODRetrofitImpl {
    private val baseURL = "https://api.nasa.gov/"

    fun getRetrofitImpl():PODAPI{
        val podRetrofitImpl = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        return podRetrofitImpl.create(PODAPI::class.java)
    }
}