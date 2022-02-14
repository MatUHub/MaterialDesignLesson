package com.example.materialdesignlesson.repository

import com.google.gson.annotations.SerializedName

data class PODServerResponse(

    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,

    //@Json(name = "media_type") версия после преобразования полученного кода с сайта,
    // @SerializedName("media_type") преобразование полученной переменной с media_type на mediaType
    @SerializedName("media_type")
    val mediaType: String,

    @SerializedName("service_version")
    val serviceVersion: String,

    val title: String,
    val url: String

)