package com.example.retrofit.network

import com.example.retrofit.data.GetAllCarResponseItem
import com.example.retrofit.data.PostRegisterResponse
import com.example.retrofit.data.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    //Memanggil semuda data yang ada di Server
    @GET("admin/car")
    fun getAllCar(): Call<List<GetAllCarResponseItem>>


    @GET("admin/car/{id}")
    fun getCarById(@Path("id") carId:Int): Call<GetAllCarResponseItem>

    //Menambahkan data ke server
    @POST("admin/auth/register")
    fun postRegister(@Body request: RegisterRequest): Call<PostRegisterResponse>

//    @POST("customer/auth/register")
//    fun addCustomer(@Body request: AddCustomerRequest): Call<AddCustomerResponse>
}