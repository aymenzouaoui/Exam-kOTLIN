package tn.esprit.nascar.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import tn.esprit.nascar.models.User

interface UserInterface {

    @POST("login")
    fun login(@Query("log") login: String, @Query("pwd") password: String): Call<User>

    @POST("/user/login")
    fun login(@Body user: User): Call<User>

    @GET("getPersons")
    fun getUsers(): Call<List<User>>
}