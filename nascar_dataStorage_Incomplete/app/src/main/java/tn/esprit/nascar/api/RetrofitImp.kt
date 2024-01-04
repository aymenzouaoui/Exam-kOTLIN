package tn.esprit.nascar.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImp {


    companion object {

        var BASE_URL = "http://10.0.2.2:9090/"

        fun buildRetrofit() : Retrofit {

            return  Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

        }
    }
}