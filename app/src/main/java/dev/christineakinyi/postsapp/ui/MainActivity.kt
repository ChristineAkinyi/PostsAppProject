package dev.christineakinyi.postsapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.christineakinyi.postsapp.databinding.ActivityMainBinding
import dev.christineakinyi.postsapp.model.Posts
import dev.christineakinyi.postsapp.viewmodel.PostsViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val postsViewModel: PostsViewModel by viewModels()
    val TAG = "MAINACTIVTYTAG"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "ONCREATE")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postsViewModel.fetchPosts()
//        postsViewModel.fetchPostsById()
//        fetchPosts()
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
    }

//    posts repo only does call apis
//where you receive click events
    override fun onResume() {
        super.onResume()
        postsViewModel.postsLiveData.observe(this, Observer { postsList->
            displayPosts(postsList)
            Log.d(TAG, "ONRESUME")
        })

        postsViewModel.errorLiveData.observe(this, Observer { error->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })
        binding.fabAddPost.setOnClickListener{
            startActivity(Intent(this, CreatePostActivity::class.java))
        }
    }

//    override fun onCreate() {
//        super.onCreate()
//        Log.d(TAG, "onCreate")
//    }

  override fun onStart(){
      super.onStart()
      Log.d(TAG, "MAINACTIVITYTAG ONSTART")
  }

//    override fun onResume(){
//        super.onResume()
//        Log.d(TAG, "onResume")
//    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "MAINACTIVITYTAG ONPAUSE")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "MAINACTIVITYTAG ONSTOP")
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

