package com.example.pemrogmobuts.network

import android.telecom.Call
import com.example.pemrogmobuts.model.DataItem
import com.example.pemrogmobuts.model.ResponseAddMatkul
import com.example.pemrogmobuts.model.ResponseMatkul
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class NetworkConfig {
    // set interceptor
    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return okHttpClient
    }
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            //.baseUrl("https://jsonplaceholder.typicode.com/")
            .baseUrl("https://172.20.10.10/aplikasi-slim/public/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService() = getRetrofit().create(Users::class.java)
}
interface Users {
    //@GET("users")
    //fun getUsers(): retrofit2.Call<List<ResponseUserItem>>

    @GET("matkul/")
    fun getMatkulAll(): retrofit2.Call<ResponseMatkul>

    @POST("matkul/")
    fun addMatkul(@Body req : DataItem): retrofit2.Call<ResponseMatkul>

    @PUT("matkul/{id}")
    fun updateMatkul(@Path("id") id : Int, @Body req : DataItem): retrofit2.Call<ResponseAddMatkul>

    @DELETE("matkul/{id}")
    fun deleteMatkul(@Path("id") id : Int): retrofit2.Call<ResponseAddMatkul>
}