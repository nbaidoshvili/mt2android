package com.example.mt2android.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mt2android.Post


@Database(entities = [Post::class],version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getPostDao():PostDao
}