package dev.christineakinyi.postsapp.api

import dev.christineakinyi.postsapp.model.Comments
import dev.christineakinyi.postsapp.ui.Posts
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApiInterface {
    @GET("/posts")
    suspend fun fetchPosts(): Response<List<Posts>>

    @GET("/posts/{postId}")
    fun fetchPostsById(@Path("postId") postId:Int): Call<Posts>

    @GET("/posts/{postId}/comments")
    fun fetchCommentsByPostId(@Path("postId") postId: Int): Call<List<Comments>>
}