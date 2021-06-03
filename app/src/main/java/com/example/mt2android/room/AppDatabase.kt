package com.example.mt2android.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [PostEntity::class],version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getPostDao():PostDao
}