package com.example.paddit.repository

import com.example.paddit.api.UserApi
import com.example.paddit.model.UserModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: UserApi
) {

    fun getSpecificUser(id: Int): Single<UserModel> {
       return getUserWithId(id)
    }

    private fun getUserWithId(id: Int): Single<UserModel> {
        return userApi.getUserWithId(id).subscribeOn(Schedulers.io())
    }
}