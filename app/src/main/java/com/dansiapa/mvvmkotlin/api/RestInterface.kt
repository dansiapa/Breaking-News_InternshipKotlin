package com.dansiapa.mvvmkotlin.api

import com.dansiapa.mvvmkotlin.model.TotalNews
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestInterface {

    @GET("v2/top-headlines")
    fun getTotalNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<TotalNews?>?

    @GET("v2/top-headlines")
    fun getTotalNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String?
    ): Call<TotalNews?>?

    @GET("v2/everything")
    fun getSearchedTotalNews(
        @Query("q") country: String,
        @Query("apiKey") apiKey: String
    ): Call<TotalNews?>?
}