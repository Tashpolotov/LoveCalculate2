package com.example.lovecalculate.view.network

import com.example.lovecalculate.main.utils.Const.API_KEY
import com.example.lovecalculate.model.LoveModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

/*    url: 'https://love-calculator.p.rapidapi.com/getPercentage',
    params: {
        sname: 'Alice',
        fname: 'John'
    },*/
   /* headers: {
        'X-RapidAPI-Key': '449c4834e9mshf57c20e5ea42df6p1a992djsn2f67d10daf64',
        'X-RapidAPI-Host': 'love-calculator.p.rapidapi.com'
    }*/

        @GET("getPercentage")
        fun getPercentage(
            @Query("fname") firstName:String,
            @Query("sname") secondName:String,
            @Header("X-RapidAPI-Key") apiKey :String = API_KEY,
            @Header("X-RapidAPI-Host") host:String = "love-calculator.p.rapidapi.com"
        ) : Call<LoveModel>
}