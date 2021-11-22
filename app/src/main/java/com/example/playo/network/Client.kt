package com.example.playo.network

import com.example.playo.models.Api
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Client {

    //default top headlines
    @GET("top-headlines")
    fun getResponse(@Query("sources") sources: String, @Query("apiKey") apiKey: String): Call<Api?>?

    //function to get top headlines on the basis of the search query
    @GET("everything")
    fun getSearchResult(@Query("q") query: String,@Query("from") date: String, @Query("sortBy") sorting: String,@Query("apiKey") apiKey: String): Call<Api?>?
}