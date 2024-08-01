package dev.christineakinyi.postsapp.repository

import dev.christineakinyi.postsapp.api.ApiClient
import dev.christineakinyi.postsapp.api.PostsApiInterface
import dev.christineakinyi.postsapp.ui.Posts
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
}