package com.example.mt2android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mt2android.api.InterfaceJson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recycler : RecyclerView
    private lateinit var adapter : RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recycler = findViewById(R.id.recyclerPosts)
        //adapter = RecyclerViewAdapter(listOf())


        //recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)


        updateData()
    }


    fun updateData(){


        val rf = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InterfaceJson::class.java)




        rf.getPosts().enqueue(object : Callback<List<Post>?> {
            override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<List<Post>?>,
                response: Response<List<Post>?>
            ) {
                Log.e("succ", response.body()!![0].body)
                adapter = RecyclerViewAdapter(response.body()!!)
                adapter.notifyDataSetChanged()
                recycler.adapter = adapter
            }
        })


    }
}