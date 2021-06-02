package com.example.mt2android

import android.app.Application
import androidx.room.Room
import com.example.mt2android.api.InterfaceJson
import com.example.mt2android.room.AppDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application(){

    lateinit var db: AppDatabase
    lateinit var rf: InterfaceJson


    companion object{
        lateinit var instance:App
            private set

    }


    override fun onCreate() {
        super.onCreate()



        rf = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InterfaceJson::class.java)


        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "APP_DATABASE"
        ).allowMainThreadQueries().build()

        instance = this

    }

}