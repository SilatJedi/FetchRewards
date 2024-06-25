package com.nouseforanappdomain.fetch.network

import com.nouseforanappdomain.fetch.model.ListItem
import retrofit2.Call
import retrofit2.http.GET

interface ListService {
    @GET("hiring.json")
    fun getList(): Call<List<ListItem>>
}