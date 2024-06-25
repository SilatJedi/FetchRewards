package com.nouseforanappdomain.fetch.network

import com.google.gson.GsonBuilder
import com.nouseforanappdomain.fetch.model.ListItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ListApi {
    private val client: ListService = Retrofit.Builder()
        .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(
            okhttp3.OkHttpClient.Builder().apply {
                callTimeout(30, TimeUnit.SECONDS)
            }.build()
        )
        .build()
        .create(ListService::class.java)

    fun getList(onSuccess: (listItems: List<ListItem>) -> Unit, onFailure: () -> Unit) {
        client.getList().enqueue(
            object : Callback<List<ListItem>> {
                override fun onResponse(
                    call: Call<List<ListItem>>,
                    response: Response<List<ListItem>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { returnedList ->
                            onSuccess.invoke(
                                returnedList
                                    .filter { it.isValid }
                                    .sortedWith(
                                        compareBy(
                                            { it.listId },
                                            {
                                                it.name?.filter { character ->
                                                    character.isDigit()
                                                }?.toInt()
                                            }
                                        )
                                    )
                            )
                        } ?: onFailure.invoke()
                    } else {
                        onFailure.invoke()
                    }
                }

                override fun onFailure(call: Call<List<ListItem>>, throwable: Throwable) {
                    onFailure.invoke()
                }
            }
        )
    }
}
