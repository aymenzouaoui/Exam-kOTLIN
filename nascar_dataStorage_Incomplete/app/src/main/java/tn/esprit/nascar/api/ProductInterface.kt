package tn.esprit.nascar.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ProductInterface {


    @GET("getProducts")
    fun getAllProducts()

}