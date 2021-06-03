package com.example.mt2android.adapters

import com.example.mt2android.api.Post
import com.example.mt2android.room.PostEntity

object PostConverter {

    fun fromDB(list: List<PostEntity>) : List<Post>{

        val dataList = mutableListOf<Post>()

        for(item in list){
            dataList.add(
                Post(
                    item.id,
                    item.userId,
                    item.body,
                    item.title
                )
            )
        }
        return dataList
    }

    fun toDB(list: List<Post>) : List<PostEntity>{

        val entityList = mutableListOf<PostEntity>()

        for(item in list){
            entityList.add(
                PostEntity(
                    item.id,
                    item.userId,
                    item.body,
                    item.title
                )
            )
        }

        return entityList
    }
}