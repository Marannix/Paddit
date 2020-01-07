package com.example.paddit.repository

import com.example.paddit.api.PostApi
import com.example.paddit.model.PostResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postApi: PostApi
) {

    fun getPosts(): Single<List<PostResponse>> {
        return retrievePostsFromApi()
    }

    private fun retrievePostsFromApi(): Single<List<PostResponse>> {
        return postApi.getPosts().map { listOfPosts ->
            return@map listOfPosts
        }.subscribeOn(Schedulers.io())
    }
}