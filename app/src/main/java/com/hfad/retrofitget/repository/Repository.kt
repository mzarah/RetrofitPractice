package com.hfad.retrofitget.repository

import com.hfad.retrofitget.api.RetrofitInstance
import com.hfad.retrofitget.model.Post
import retrofit2.Call
import retrofit2.Response

class Repository {

    suspend fun getPost(number: Int): Response<Post> {
        return RetrofitInstance.api.getPost(number)
    }

    suspend fun getPostsByUserId(userId: Int, options: Map<String, String>): Response<List<Post>> {
        return RetrofitInstance.api.getPostsByUserId(userId, options)
    }

    suspend fun pushPost(post: Post): Response<Post> {
        return RetrofitInstance.api.pushPost(post)
    }

}