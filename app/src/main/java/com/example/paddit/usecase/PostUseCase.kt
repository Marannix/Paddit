package com.example.paddit.usecase

import com.example.paddit.repository.PostRepository
import com.example.paddit.state.PostDataState
import io.reactivex.Single
import javax.inject.Inject

class PostUseCase @Inject constructor(
    private val repository: PostRepository
) {

    fun getPosts(): Single<PostDataState> {
        return repository.getPosts()
            .map<PostDataState> { listOfPosts ->
                PostDataState.Success(listOfPosts)
            }.onErrorReturn { error ->
                PostDataState.GenericError(error.message)
            }
    }
}