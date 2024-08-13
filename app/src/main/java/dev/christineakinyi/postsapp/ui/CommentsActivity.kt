package dev.christineakinyi.postsapp.ui

import dev.christineakinyi.postsapp.api.ApiClient
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.christineakinyi.postsapp.R
import dev.christineakinyi.postsapp.model.Comments
import dev.christineakinyi.postsapp.api.PostsApiInterface
import dev.christineakinyi.postsapp.databinding.ActivityCommentsBinding
import dev.christineakinyi.postsapp.model.Posts
import dev.christineakinyi.postsapp.viewmodel.PostsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CommentsActivity : AppCompatActivity() {
    private var postId = 0
    val postsViewModel : PostsViewModel by viewModels()
    val TAG = "MYTAG"
    private lateinit var binding: ActivityCommentsBinding
//    private lateinit var commentsAdapter: CommentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "COMMENTSACTIVITY ONCREATE")
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)

        val extra = intent.extras
        if (extra != null) {
            postId = extra.getInt("POST_ID")
            postsViewModel.fetchPostsById(postId)
            postsViewModel.fetchComments(postId)

        }
        binding.rvComments.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "COMMENTSACTIVITY ONSTART")
    }

    override fun onResume() {
        super.onResume()
        postsViewModel.postLiveData.observe(this){ post ->
            binding.tvPostTitle.text = post?.title
            binding.tvPostBody.text = post?.body
        }

        postsViewModel.errorLiveData.observe(this, Observer {
                error -> Toast.makeText(this@CommentsActivity, error, Toast.LENGTH_LONG).show()
        })
        postsViewModel.commentsLiveData.observe(this, Observer {commentsList ->
            displayComments(commentsList)
        })
    }
    fun displayComments(commentsList : List<Comments>){
        binding.rvComments.adapter = CommentsAdapter(commentsList)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "COMMENTSACTIVITY ONPAUSE")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "COMMENTSACTIVITY ONSTOP")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "COMMENTSACTIVITY ONDESTROY")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }










//class CommentsActivity : AppCompatActivity() {
//   private var postId = 0
//    val postsViewModel: PostsViewModel by viewModels()
//    val TAG  = "MYTAG"
//    private lateinit var binding: ActivityCommentsBinding
//    private lateinit var commentsAdapter: CommentsAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityCommentsBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//       val extra = intent.extras
//        if (extra != null){
//            postId = extra.getInt("POST_ID")
//            postsViewModel.fetchPosts()
//            postsViewModel.fetchComments(postId)
////            fetchPost(postId)
//
//            postsViewModel.fetchPostsById(postId)
//        }
//        setupRecyclerView()
//    }
//
//    private fun setupRecyclerView(){
//        commentsAdapter = CommentsAdapter(emptyList())
//        binding.rvComments.layoutManager = LinearLayoutManager(this)
//        binding.rvComments.adapter = commentsAdapter
//    }

//    fun fetchPost(postId: Int){
//        val apiClient = ApiClient.buildApiClient(PostsApiInterface::class.java)
//        val request = apiClient.fetchPostsById(postId)
//
//        request.enqueue(object : Callback<Posts> {
//            override fun onResponse(p0: Call<Posts>, p1: Response<Posts>) {
//                if (p1.isSuccessful){
//                    val post = p1.body()
//                    binding.tvPostTitle.text = post?.title
//                    binding.tvPostBody.text = post?.body
//                } else {
//                    Toast.makeText(this@CommentsActivity,
//                        p1.errorBody()?.string(),
//                        Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(p0: Call<Posts>, t: Throwable) {
//                Toast.makeText(baseContext, t.message,Toast.LENGTH_LONG).show()
//            }
//        })
//    }

//    private suspend fun fetchComments(postId: Int){
//        val apiClient = ApiClient.buildApiClient(PostsApiInterface::class.java)
//        val request = apiClient.fetchComments(postId)
//        request.enqueue(object : Callback<List<Comments>> {
//            override fun onResponse(p0: Call<List<Comments>>, p1: Response<List<Comments>>) {
//                if (p1.isSuccessful){
//                    val comments = p1.body() ?: emptyList()
//                    if (comments.isNotEmpty()){
//                        commentsAdapter.commentsList = comments
//                        commentsAdapter.notifyDataSetChanged()
//                    }   else {
//                        Toast.makeText(this@CommentsActivity, "No comments found", Toast.LENGTH_LONG
//                        ).show()
//                    }
//                } else {
//                    Toast.makeText(this@CommentsActivity, "Error:${p1.message()}", Toast.LENGTH_LONG).show()
//                }
//            }
//
//            override fun onFailure(p0: Call<List<Comments>>, t: Throwable) {
//                Toast.makeText(this@CommentsActivity, "Failure: ${t.message}",
//                    Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//
//    override fun onResume() {
//        super.onResume()
//        postsViewModel.postsLiveData.observe(this) { post ->
//            binding.tvPostTitle.text = post?
//            binding.tvPostBody.text = post?
//        }
//
//        postsViewModel.errorLiveData.observe(this, Observer { error ->
//
//        })
//    }
//
//    fun displayComments(commentsList: List<Comments>){
//        binding.rvComments.adapter = CommentsAdapter(commentsList)
//    }
}