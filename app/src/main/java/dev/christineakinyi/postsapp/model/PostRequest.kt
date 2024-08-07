package dev.christineakinyi.postsapp.model

data class PostRequest(
    var userId: Int,
    var title: String,
    var body: String
)
