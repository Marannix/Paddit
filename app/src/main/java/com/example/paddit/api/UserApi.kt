package com.example.paddit.api

import com.example.paddit.model.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users")
    fun getUsers(): Single<List<UserResponse>>

    @GET("users/{id}")
    fun getUserWithId(@Path("id") id: Int): Single<UserResponse>
}