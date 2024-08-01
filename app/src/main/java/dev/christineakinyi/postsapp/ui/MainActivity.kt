package dev.christineakinyi.postsapp.ui

import android.content.res.Configuration
import dev.christineakinyi.postsapp.api.ApiClient
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.christineakinyi.postsapp.api.PostsApiInterface
import dev.christineakinyi.postsapp.databinding.ActivityMainBinding
import dev.christineakinyi.postsapp.viewmodel.PostsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val postsViewModel: PostsViewModel by viewModels()
    val TAG = "MAINACTIVTYTAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postsViewModel.fetchPosts()
//        fetchPosts()
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
    }

//    posts repo only does call apis

    override fun onResume() {
        super.onResume()
        postsViewModel.postsLiveData.observe(this, Observer { postsList->
            displayPosts(postsList)
        })

        postsViewModel.errorLiveData.observe(this, Observer { error->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

  override fun onStart(){
      super.onStart()
      Log.d(TAG, "onStart")
  }

    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }
    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    fun displayPosts(posts: List<Posts>){
        val postsAdapter = PostsAdapter(posts, this)
        binding.rvPosts.adapter = postsAdapter
    }

}

//    fun fetchPosts(){
//        val apiInterface = ApiClient.buildApiClient(PostsApiInterface::class.java)
//        val request = apiInterface.fetchPosts()
//        request.enqueue(object  : Callback<List<Posts>> {
//            override fun onResponse(p0: Call<List<Posts>>, p1: Response<List<Posts>>) {
//                if (p1.isSuccessful){
//                    val posts = p1.body()
//                    if (posts != null){
//                        displayPosts(posts)
//                    }
////                    displayPosts(posts!!)
//                    Toast.makeText(baseContext, "Fetched${posts!!.size}posts",
//                        Toast.LENGTH_LONG
//                        ).show()
//                    }
//                else{
//                    Toast.makeText(baseContext, p1.errorBody()?.string(), Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(p0: Call<List<Posts>>, p1: Throwable) {
//                Toast.makeText(baseContext, p1.message, Toast.LENGTH_LONG).show()
//            }
//        })
//
//    }
//

