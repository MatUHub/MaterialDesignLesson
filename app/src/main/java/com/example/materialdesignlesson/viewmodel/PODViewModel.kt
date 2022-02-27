package com.example.materialdesignlesson.viewmodel

import android.content.Context

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesignlesson.BuildConfig
import com.example.materialdesignlesson.repository.PODRetrofitImpl
import com.example.materialdesignlesson.repository.PODServerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class PODViewModel(private val liveData: MutableLiveData<PODData> = MutableLiveData(),
                   private val  podRetrofitImpl: PODRetrofitImpl = PODRetrofitImpl()): ViewModel() {
    //получение данных из LiveData
    fun getData():LiveData<PODData>{
        return liveData
    }
    //
    fun sendRequest(data: Int){
        liveData.postValue(PODData.Loading(null))
        podRetrofitImpl.getRetrofitImpl().getPOD(BuildConfig.NASA_API_KEY, takeDate(data)).enqueue(
            object : Callback<PODServerResponse> {
                override fun onResponse(
                    call: Call<PODServerResponse>,
                    response: Response<PODServerResponse>
                ) {
                    if (response.isSuccessful&&response.body()!=null){
                        response.body()?.let {
                            liveData.postValue(PODData.Success(it))
                        }

                    } else{
                        response.body()?.let {
                            liveData.postValue(PODData.Error(it))}
                    }

                }

                override fun onFailure(call: Call<PODServerResponse>, t: Throwable) {
                    Log.d("Error", "Server not found")
                }
            }
        )
    }

    private fun takeDate(count: Int): String {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_MONTH, count)
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("EST")
        return format.format(currentDate.time)
    }



}