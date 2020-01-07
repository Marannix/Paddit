package com.example.paddit

import io.reactivex.Single
import retrofit2.http.GET

interface PostApi {

    @GET("posts")
    fun getPosts() : Single<List<PostResponse>>
}
