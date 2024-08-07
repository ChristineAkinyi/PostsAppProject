package dev.christineakinyi.postsapp.repository

import dev.christineakinyi.postsapp.api.ApiClient
import dev.christineakinyi.postsapp.api.PostsApiInterface
import dev.christineakinyi.postsapp.model.Comments
import dev.christineakinyi.postsapp.model.PostRequest
import dev.christineakinyi.postsapp.model.Posts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PostsRepository {
    val apiClient = ApiClient.buildApiClient(PostsApiInterface::class.java)

    suspend fun fetchPosts(): Response<List<Posts>>{
        return withContext(Dispatchers.IO){
            apiClient.fetchPosts()
        }
    }

    suspend fun fetchPostsById(postId: Int): Response<Posts>{
        return withContext(Dispatchers.IO){
            apiClient.fetchPostsById(postId)
        }
    }

    suspend fun fetchComments(postId: Int): Response<List<Comments>>{
        return withContext(Dispatchers.IO){
            apiClient.fetchComments(postId)
        }
    }

    suspend fun createPost(postRequest: PostRequest): Response<Posts>{
//      set of threads designed to make network calls disptachers.IO
        return withContext(Dispatchers.IO){
            apiClient.createPost(postRequest)
        }
    }
}