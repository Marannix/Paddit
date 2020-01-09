package com.example.paddit.model

data class UsersModel(
    val posts: List<PostResponse>,
    val user: List<UserResponse>
)