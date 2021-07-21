package com.hfad.retrofitget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hfad.retrofitget.model.Post
import com.hfad.retrofitget.repository.Repository

class NewPostActivity : AppCompatActivity() {

    private val btn4 by lazy {
        findViewById(R.id.button4) as Button
    }
    private val postId by lazy {
        findViewById(R.id.postId) as EditText
    }
    private val userId by lazy {
        findViewById(R.id.userId) as EditText
    }
    private val title by lazy {
        findViewById(R.id.title2) as EditText
    }
    private val desc by lazy {
        findViewById(R.id.desc) as EditText
    }

    private lateinit var viewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        val repository = Repository()
        val viewModelFactory = PostViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PostViewModel::class.java)



        btn4.setOnClickListener {
            val id: Int = Integer.parseInt(postId.text.toString())
            val uid: Int = Integer.parseInt(userId.text.toString())
            val title: String = title.text.toString()
            val desc: String = desc.text.toString()

            val newPost = Post(uid, id, title, desc)

            viewModel.pushPost(newPost)
            viewModel.newPostResponse.observe(this, Observer {

                if (it.isSuccessful) {
                    Log.d("RESPONSE", it.body().toString())
                    Toast.makeText(this, "Post is sent", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d("ERROR", it.errorBody().toString())
                    Toast.makeText(this, it.code().toString(), Toast.LENGTH_SHORT).show()
                }
            })


        }
    }


}