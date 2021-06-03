package com.example.mt2android.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface InterfaceAPI {

    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts/{postId}/comments")
    fun getComments(@Path("postId") postId:Int): Call<List<Comment>>

}