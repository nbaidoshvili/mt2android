package com.example.mt2android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mt2android.adapters.CommentsRecyclerAdapter
import com.example.mt2android.adapters.PostsRecyclerAdapter
import com.example.mt2android.api.Post
import kotlinx.coroutines.*
import retrofit2.awaitResponse
import java.lang.Exception

class CommentsActivity : AppCompatActivity() {

    private lateinit var postTitle: TextView
    private lateinit var postBody: TextView
    private lateinit var adapter: CommentsRecyclerAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var numberOfComments: TextView
    private lateinit var refresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        val post = intent.extras?.getParcelable<Post>("POST")
        postTitle = findViewById(R.id.postDisplayTitle)
        postBody = findViewById(R.id.postDisplayBody)
        recycler = findViewById(R.id.recyclerComments)
        recycler.layoutManager = LinearLayoutManager(this)
        numberOfComments = findViewById(R.id.commentsNumber)
        refresh = findViewById(R.id.refreshComments)


        postTitle.text = post?.title
        postBody.text = post?.body

        getDataAPI(post?.id)

        refresh.setOnRefreshListener {
            getDataAPI(post?.id)
        }

    }

    fun getDataAPI(postId: Int?){
        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response = App.instance.api.getComments(postId!!).awaitResponse()


                if (response.isSuccessful) {
                    val list = response.body()!!

                    withContext(Dispatchers.Main) {
                        adapter = CommentsRecyclerAdapter(list)
                        adapter.notifyDataSetChanged()
                        recycler.adapter = adapter
                        numberOfComments.text = list.size.toString()
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
}