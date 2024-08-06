package dev.christineakinyi.postsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.christineakinyi.postsapp.model.Comments
import dev.christineakinyi.postsapp.repository.PostsRepository
import dev.christineakinyi.postsapp.model.Posts
import kotlinx.coroutines.launch

class PostsViewModel: ViewModel() {
    val postsRepo = PostsRepository()
    val errorLiveData = MutableLiveData<String>()
    val postsLiveData = MutableLiveData<List<Posts>>()
    val postLiveData = MutableLiveData<Posts>()
    val commentsLiveData = MutableLiveData<List<Comments>>()

    fun fetchPosts(){
        viewModelScope.launch {
            val response = postsRepo.fetchPosts()
            if (response.isSuccessful){
                postsLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

    fun fetchPostsById(postId:Int){
        viewModelScope.launch {
            val response = postsRepo.fetchPostsById(postId)
            if (response.isSuccessful){
                postLiveData.postValue(response.body())
            }
            else {
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

    fun fetchComments(postId: Int){
        viewModelScope.launch {
            val response = postsRepo.fetchComments(postId)
            if(response.isSuccessful) {
                commentsLiveData.postValue(response.body())
            } else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}