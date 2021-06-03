package com.example.mt2android

import android.app.Application
import androidx.room.Room
import com.example.mt2android.api.InterfaceAPI
import com.example.mt2android.room.AppDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application(){

    lateinit var db: AppDatabase
    lateinit var api: InterfaceAPI


    companion object{
        lateinit var instance:App
            private set

    }


    override fun onCreate() {
        super.onCreate()



        api = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InterfaceAPI::class.java)


        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "APP_DATABASE"
        ).build()

        instance = this

    }

}