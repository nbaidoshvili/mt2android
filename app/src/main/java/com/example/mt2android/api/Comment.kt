package com.example.mt2android.api

import com.google.gson.annotations.SerializedName

data class Comment(


    var postId: Int,

    @SerializedName("id")
    var commentId: Int,

    var name: String,

    var email: String,

    var body: String

)
