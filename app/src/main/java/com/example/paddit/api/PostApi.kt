package com.example.paddit.api

import com.example.paddit.model.PostResponse
import io.reactivex.Single
import retrofit2.http.GET

interface PostApi {

    @GET("posts")
    fun getPosts() : Single<List<PostResponse>>
}
