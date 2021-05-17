package com.example.aop_part3_chapter07

import retrofit2.Call
import retrofit2.http.GET

interface HouseService {
    @GET("/v3/7ae04a67-571d-4d04-bb71-61b96b5c1e66")
    fun getHouseList() : Call<HouseDto>
}