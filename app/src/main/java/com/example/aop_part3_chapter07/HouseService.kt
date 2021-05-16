package com.example.aop_part3_chapter07

import retrofit2.Call
import retrofit2.http.GET

interface HouseService {
    @GET("/v3/fbff87d8-afb5-43ea-9ab9-f3f796874ea5")
    fun getHouseList() : Call<HouseDto>
}