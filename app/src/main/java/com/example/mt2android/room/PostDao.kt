package com.example.mt2android.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mt2android.Post

@Dao
interface PostDao{

    @Query("SELECT * FROM POSTS")
    fun getAllPosts():List<Post>

    @Query("DELETE FROM POSTS")
    fun deleteAllPosts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg post: Post)
}