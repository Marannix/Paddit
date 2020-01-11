package com.example.paddit.repository

import com.example.paddit.api.UserApi
import com.example.paddit.data.UserDao
import com.example.paddit.model.UserResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: UserApi,
    private val userDao: UserDao
) {

    fun getUsers(): Single<List<UserResponse>> {
       return retrieveUsersFromDb()
    }

    private fun retrieveUsersFromDb() : Single<List<UserResponse>> {
        return userDao.getUsers().
            flatMap { listOfUsers ->
                if (listOfUsers.isEmpty()) {
                    // TODO: Same issue with posts, need to find a way to update the database
                    // Maybe I should clear the database or something?
                    getUsersFromApi()
                } else {
                    Single.just(listOfUsers)
                }
            }
    }

    private fun getUsersFromApi(): Single<List<UserResponse>> {
        return userApi.getUsers().doOnSuccess {listOfUsers ->
          userDao.insertUsers(listOfUsers)
        }.subscribeOn(Schedulers.io())
    }


}