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

    /**
     *  Get the posts information from the database, if empty then request from api
     */
    private fun retrievePostsFromDb(): Single<List<PostResponse>> {
        return postsDao.getPosts()
            .flatMap { listOfPosts ->
                if (listOfPosts.isEmpty()) {
                    // This isn't good for a production application as the list isn't updated once populated
                    retrievePostsFromApi()
                } else {
                    Single.just(listOfPosts)
                }
            }
    }

    /**
     *  Get the posts information from the api
     */
    private fun retrievePostsFromApi(): Single<List<PostResponse>> {
        return postApi.getPosts()
            .doOnSuccess {
                postsDao.insertPosts(it)
            }.subscribeOn(Schedulers.io())
    }

}