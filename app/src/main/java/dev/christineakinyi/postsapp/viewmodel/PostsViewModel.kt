package dev.christineakinyi.postsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.christineakinyi.postsapp.repository.PostsRepository
import dev.christineakinyi.postsapp.model.Posts
import kotlinx.coroutines.launch

class PostsViewModel: ViewModel() {
    val postsRepo = PostsRepository()
    val errorLiveData = MutableLiveData<String>()
    val postsLiveData = MutableLiveData<List<Posts>>()

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

}