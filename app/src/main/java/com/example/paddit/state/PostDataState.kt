package com.example.paddit.state

import com.example.paddit.model.PostResponse

sealed class PostDataState {
    data class Success(val posts: List<PostResponse>) : PostDataState()
    data class GenericError(val errorMessage: String?) : PostDataState()
}