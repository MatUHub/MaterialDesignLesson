package com.example.materialdesignlesson.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesignlesson.BuildConfig
import com.example.materialdesignlesson.repository.PODRetrofitImpl
import com.example.materialdesignlesson.repository.PODServerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PODViewModel(private val liveData: MutableLiveData<PODData> = MutableLiveData(),
                   private val  podRetrofitImpl: PODRetrofitImpl = PODRetrofitImpl()): ViewModel() {
    //получение данных из LiveData
    fun getData():LiveData<PODData>{
        return liveData
    }
    //
    fun sendRequest(){
        liveData.postValue(PODData.Loading(null))
        podRetrofitImpl.getRetrofitImpl().getPOD(BuildConfig.NASA_API_KEY).enqueue(
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
                        //TODO Вывести ошибку получения данных
                    }

                }

                override fun onFailure(call: Call<PODServerResponse>, t: Throwable) {
                    //TODO("Not yet implemented")
                }
            }
        )
    }
}