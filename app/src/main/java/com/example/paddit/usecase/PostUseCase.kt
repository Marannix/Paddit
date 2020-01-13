package com.example.paddit.usecase

import com.example.paddit.model.PostResponse
import com.example.paddit.repository.PostRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Was hoping to return a data state to the viewmodel which will contain wither a success or an error state
 * However, I went with a different approach due to needing two different sources at the same time
 */
class PostUseCase @Inject constructor(
    private val repository: PostRepository
) {

    /**
     * TODO: Think about whether or not I should use DataStates
     */

//    fun getPosts(): Single<PostDataState> {
//        return repository.getPosts()
//            .map<PostDataState> { listOfPosts ->
//                PostDataState.Success(listOfPosts)
//            }.onErrorReturn { error ->
//                PostDataState.GenericError(error.message)
//            }
//    }

    fun getPosts(): Single<List<PostResponse>> {
        return repository.getPosts().onErrorResumeNext { Single.just(emptyList()) }
    }
}