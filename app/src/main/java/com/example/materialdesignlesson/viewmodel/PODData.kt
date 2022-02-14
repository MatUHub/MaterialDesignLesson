package com.example.materialdesignlesson.viewmodel

import com.example.materialdesignlesson.repository.PODServerResponse

sealed class PODData {
    data class Success(val serverResponse: PODServerResponse):PODData()
    data class Error(val error: Throwable):PODData()
    data class Loading(val process: Int?):PODData()
}