package com.example.paddit.repository

import com.example.paddit.api.UserApi
import com.example.paddit.model.UserModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: UserApi
) {

    fun getUsers(): Single<UserModel> {
       return getUsersFromApi()
    }

    private fun getUsersFromApi(): Single<UserModel> {
        return userApi.getUsers().subscribeOn(Schedulers.io())

    }
}