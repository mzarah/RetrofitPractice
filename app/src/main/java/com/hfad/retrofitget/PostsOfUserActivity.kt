package com.hfad.retrofitget

import android.content.Intent
import android.content.Intent.getIntent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.retrofitget.adapter.PostsListAdapter
import com.hfad.retrofitget.repository.Repository
import java.util.Observer

class PostsOfUserActivity : AppCompatActivity() {

    private val recyclerView by lazy {
        findViewById(R.id.postsListRecycler) as RecyclerView
    }

    private val postsAdapter by lazy { PostsListAdapter() }
    private lateinit var viewModel: PostViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts_of_user)

        SetUpRecyclerView()

        var intent1: Intent = getIntent()
        val userId = intent1.getStringExtra("userId")

        val repository = Repository()
        val viewModelFactory = PostViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PostViewModel::class.java)


        val options: HashMap<String, String> = HashMap()
        options["_sort"] = "id"
        options["_order"] = "desc"

        viewModel.getPostsByUserId(Integer.parseInt(userId), options)

        viewModel.postsOfUserResponse.observe(this, androidx.lifecycle.Observer {
            if (it.isSuccessful) {
                it.body()?.let { it -> postsAdapter.setData(it) }
            } else {
                Log.d("ERROR", it.errorBody().toString())
                Toast.makeText(this, it.code().toString(), Toast.LENGTH_SHORT).show()
            }
        }
        )

    }



    private fun SetUpRecyclerView() {
        recyclerView.adapter = postsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}

