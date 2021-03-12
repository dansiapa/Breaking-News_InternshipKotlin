package com.dansiapa.mvvmkotlin.model

import android.widget.ImageView
import com.google.gson.annotations.SerializedName
import java.util.*


data class News(

    private val source: String,
    private val title: String,
    private val description: String,
    private val url: String,
    private val urlToImage: String,
    private val publishedAt: Date,
    private val imgUrl: String

//Image Binding - I didn't write newsviewmodel for just this method
//    @BindingAdapter(["bind:imgUrl"])
//    fun setImage(imageView: ImageView, imgUrl: String?) {
//    Glide.with(imageView.context).load(imgUrl).into(imageView)
//}
)