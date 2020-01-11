package com.example.paddit.repository

import com.example.paddit.api.PostApi
import com.example.paddit.data.PostsDao
import com.example.paddit.model.PostResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postApi: PostApi,
    private val postsDao: PostsDao
) {

    fun getPosts(): Single<List<PostResponse>> {
        return retrievePostsFromDb()
    }

    private fun retrievePostsFromDb(): Single<List<PostResponse>> {
        return postsDao.getPosts()
            .flatMap { listOfPosts ->
                if (listOfPosts.isEmpty()) {
                    // TODO: But wait... doesn't this mean the database isn't updated??
                    // Maybe i should have a refresh button
                    retrievePostsFromApi()
                } else {
                    Single.just(listOfPosts)
                }
            }
    }

    private fun retrievePostsFromApi(): Single<List<PostResponse>> {
        return postApi.getPosts()
            .doOnSuccess {
                postsDao.insertPosts(it)
            }.subscribeOn(Schedulers.io())
    }

}