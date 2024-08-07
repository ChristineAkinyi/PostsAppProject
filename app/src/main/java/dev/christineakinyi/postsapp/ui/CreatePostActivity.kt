package dev.christineakinyi.postsapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import dev.christineakinyi.postsapp.R
import dev.christineakinyi.postsapp.databinding.ActivityCreatePostBinding
import dev.christineakinyi.postsapp.databinding.ActivityMainBinding
import dev.christineakinyi.postsapp.model.PostRequest
import dev.christineakinyi.postsapp.viewmodel.PostsViewModel

class CreatePostActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreatePostBinding
    val postsViewModel: PostsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
    }

    override fun onResume() {
        super.onResume()
        binding.btnPost.setOnClickListener{ validatePost()}

        postsViewModel.createPostLiveData.observe(this){ message ->
            binding.progressBar2.visibility = View.GONE
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            clearForm()
        }

        postsViewModel.errorLiveData.observe(this){ error ->
            binding.progressBar2.visibility = View.GONE
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }

    fun validatePost() {
        val userId = binding.etUserId.text.toString()
        val title = binding.etTitle.text.toString()
        val body = binding.etBody.text.toString()

        var error = false

        if(userId.isBlank()){
            binding.etUserId.error = "User id is required"
            error = true
        }

        if(body.isBlank()){
            binding.etTitle.error = "Title is required"
            error = true
        }

        if(title.isBlank()){
            binding.etTitle.error = "Title is required"
        }

        if(!error){
            binding.progressBar2.visibility = View.VISIBLE
            val postRequest = PostRequest(userId = userId.toInt(), title = title, body = body)
            postsViewModel.createPost(postRequest)
        }
    }

    fun clearForm(){
        binding.etUserId.text.clear()
        binding.etTitle.text.clear()
        binding.etBody.text.clear()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}

//toolbar
//progressbar
//comments activity has onsupport navigation, up arrow