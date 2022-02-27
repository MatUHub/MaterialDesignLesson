package com.example.materialdesignlesson.repository


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface PODAPI {
    //andpoint в сслыке на ресурс
    @GET("planetary/apod")
    //"api_key" параметр запроса
    fun getPOD(@Query("api_key") apiKey:String, @Query("date") date:String ): Call<PODServerResponse>
}