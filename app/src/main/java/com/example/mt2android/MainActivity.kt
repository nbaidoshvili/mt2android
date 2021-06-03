package com.example.mt2android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mt2android.adapters.PostConverter
import com.example.mt2android.adapters.PostsRecyclerAdapter
import kotlinx.coroutines.*
import retrofit2.*
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private lateinit var recycler : RecyclerView
    private lateinit var adapter : PostsRecyclerAdapter
    private lateinit var numberOfPosts: TextView
    private lateinit var refresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recyclerPosts)
        recycler.layoutManager = LinearLayoutManager(this)
        numberOfPosts = findViewById(R.id.postsNumber)
        refresh = findViewById(R.id.refreshPosts)


        GlobalScope.launch(Dispatchers.IO) {
            if(App.instance.db.getPostDao().getAllPosts().isEmpty()){

                getDataAPI()
            }
            else{

                getDataDB()
            }
        }

        refresh.setOnRefreshListener {
            getDataAPI()
        }
    }


    fun getDataAPI(){
        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response = App.instance.api.getPosts().awaitResponse()

                if (response.isSuccessful) {
                    val list = response.body()!!

                    val job1 = launch {
                        App.instance.db.getPostDao().deleteAllPosts()

                        for (post in PostConverter.toDB(list)) {
                            App.instance.db.getPostDao().insert(post)
                        }
                    }


                    val job2 = launch {
                        withContext(Dispatchers.Main) {
                            adapter = PostsRecyclerAdapter(list, this@MainActivity)
                            adapter.notifyDataSetChanged()
                            recycler.adapter = adapter
                            numberOfPosts.text = list.size.toString()
                        }
                    }

                    job1.join()
                    job2.join()

                    withContext(Dispatchers.Main) {
                        refresh.isRefreshing = false
                    }
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                    refresh.isRefreshing = false
                }
            }
        }
    }

    fun getDataDB(){

        GlobalScope.launch(Dispatchers.IO) {

            try {
                val list = PostConverter.fromDB(App.instance.db.getPostDao().getAllPosts())

                withContext(Dispatchers.Main) {
                    adapter = PostsRecyclerAdapter(list, this@MainActivity)
                    adapter.notifyDataSetChanged()
                    recycler.adapter = adapter
                    numberOfPosts.text = list.size.toString()
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}