package com.example.mt2android.api

import com.example.mt2android.Post
import retrofit2.Call
import retrofit2.http.GET

interface InterfaceAPI {

    @GET("posts")
    fun getPosts(): Call<List<Post>>

}