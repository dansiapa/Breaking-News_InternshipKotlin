package com.dansiapa.mvvmkotlin.model

data class TotalNews(

    private var status: String,
    private var totalNewsCount: Int,
    private var newsList: List<News?>,

)