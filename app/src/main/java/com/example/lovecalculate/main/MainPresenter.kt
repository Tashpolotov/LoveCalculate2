package com.example.lovecalculate.main

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lovecalculate.R
import com.example.lovecalculate.model.LoveModel
import com.example.lovecalculate.model.RetrofitService
import com.example.lovecalculate.view.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainPresenter {
    
    val api = RetrofitService().api
    val TAG = "ololo"

    lateinit var view:MainView

    fun getData(firstName:String, secondName:String){
        api.getPercentage(firstName, secondName
        ).enqueue(object:Callback<LoveModel>{
            override fun onResponse(call: Call<LoveModel>, response: Response<LoveModel>) {
                if(response.isSuccessful){
                    val model = response.body()
                    model?.let {
                        App.dataBase.getLoveDao().insert(it)
                        view.changeScreen(it)
                    }

                }
            }

            override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun attachView(view: MainView){
        this.view = view
    }
}