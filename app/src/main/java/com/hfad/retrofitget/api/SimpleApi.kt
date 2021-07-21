package com.hfad.retrofitget.api

import com.hfad.retrofitget.model.Post
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {

    @GET("posts/{postNumber}")
     suspend fun getPost(
            @Path("postNumber") number: Int
    ): Response<Post>

    @GET("posts")
    suspend fun getPostsByUserId(
            @Query("userId") userId: Int,
            @QueryMap options: Map<String, String>
    ): Response<List<Post>>

    @POST("posts")
    suspend fun pushPost(
            @Body post: Post
    ): Response<Post>

}