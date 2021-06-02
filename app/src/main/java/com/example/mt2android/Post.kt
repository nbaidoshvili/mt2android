package com.example.mt2android

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "POSTS")
data class Post(

    @PrimaryKey
    @ColumnInfo(name = "POST_ID")
    var id: Int,

    @ColumnInfo(name = "USER_ID")
    var userId: Int,

    @ColumnInfo(name = "BODY")
    var body: String,

    @ColumnInfo(name = "TITLE")
    var title: String
)