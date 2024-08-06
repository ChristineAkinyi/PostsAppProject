package dev.christineakinyi.postsapp.api

import dev.christineakinyi.postsapp.model.Comments
import dev.christineakinyi.postsapp.model.Posts
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApiInterface {
    @GET("/posts")
    suspend fun fetchPosts(): Response<List<Posts>>

    @GET("/posts/{postId}")
    suspend  fun fetchPostsById(@Path("postId") postId:Int): Response<Posts>

    @GET("/posts/{postId}/comments")
    suspend fun fetchComments(@Path("postId") postId: Int) : Response<List<Comments>>
//  fun fetchCommentsByPostId(@Path("postId") postId: Int): Call<List<Comments>>
}