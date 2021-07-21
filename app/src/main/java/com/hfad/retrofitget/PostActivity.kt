package com.hfad.retrofitget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hfad.retrofitget.repository.Repository

class PostActivity : AppCompatActivity() {

    private lateinit var viewModel: PostViewModel

    private val number by lazy {
        findViewById(R.id.num_Input) as EditText
    }
    private val btn by lazy {
        findViewById(R.id.button) as Button
    }

    private val btn2 by lazy {
        findViewById(R.id.button2) as Button
    }

    private val btn3 by lazy {
        findViewById(R.id.button3) as Button
    }

    private val title_txt by lazy {
        findViewById(R.id.title_text) as TextView
    }
    private val desc_txt by lazy {
        findViewById(R.id.desc_text) as TextView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val repository = Repository()
        val viewModelFactory = PostViewModelFactory(repository)
  viewModel = ViewModelProvider(this, viewModelFactory).get(PostViewModel::class.java)

        btn.setOnClickListener {
            val num: String = number.text.toString()
            viewModel.getPost(Integer.parseInt(num))
            viewModel.postResponse.observe(this, Observer {

                if (it.isSuccessful) {
                    if (it.body()?.id==5) title_txt.setTextColor(0xff0000ff.toInt())
                    title_txt.text =  getString(R.string.title).plus(it.body()?.title.toString())
                    desc_txt.text = getString(R.string.description).plus(it.body()?.body.toString())
                    Log.d("TITLE", it.body()?.title.toString())
                } else {
                    Log.d("ERROR", it.errorBody().toString())
                    Toast.makeText(this, it.code().toString(), Toast.LENGTH_SHORT).show()
                }
            })
        }

        btn2.setOnClickListener {
            val intent = Intent(this@PostActivity, PostsOfUserActivity::class.java);
            val userId: String = number.text.toString()
            intent.putExtra("userId", userId)
            startActivity(intent);
        }

        btn3.setOnClickListener {
            val intent = Intent(this@PostActivity, NewPostActivity::class.java);
            startActivity(intent);
        }

    }
}