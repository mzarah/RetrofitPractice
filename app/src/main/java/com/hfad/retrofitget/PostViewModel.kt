package com.hfad.retrofitget

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.retrofitget.model.Post
import com.hfad.retrofitget.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class PostViewModel(private val repository: Repository): ViewModel() {

    val postResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val postsOfUserResponse: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val newPostResponse: MutableLiveData<Response<Post>> = MutableLiveData()


    fun getPost(number: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<Post> = repository.getPost(number)
                postResponse.postValue(response)
            }
           catch (e: Exception){
                 Log.d("PostViewModel class", e.message)
            }


        }
    }

    fun getPostsByUserId(userId: Int, options: Map<String, String>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<List<Post>> = repository.getPostsByUserId(userId, options)
                postsOfUserResponse.postValue(response)
            }
            catch (e: Exception){
                Log.d("PostViewModel class", e.message)
            }
        }
    }

    fun pushPost(post: Post) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<Post> = repository.pushPost(post)
                newPostResponse.postValue(response)
            }
            catch (e: Exception){
                Log.d("PostViewModel class", e.message)
            }
        }
    }


}