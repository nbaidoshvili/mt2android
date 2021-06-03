package com.example.mt2android.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "POSTS")
data class PostEntity(

    @PrimaryKey
    @ColumnInfo(name = "POST_ID")
    var id: Int,

    @ColumnInfo(name = "USER_ID")
    var userId: Int,

    @ColumnInfo(name = "BODY")
    var body: String?,

    @ColumnInfo(name = "TITLE")
    var title: String?
)
