package com.example.paddit.usecase

import com.example.paddit.model.UserResponse
import com.example.paddit.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    /**
     * TODO: Think about whether or not I should use DataStates
     */

//    fun getUsers(): Single<UserDataState> {
//        return userRepository.getUsers()
//            .map<UserDataState> { listOfUsers ->
//                UserDataState.Success(listOfUsers)
//            }.onErrorReturn {
//                UserDataState.GenericError(it.message)
//            }
//    }

    fun getUsers(): Single<List<UserResponse>> {
        return userRepository.getUsers().onErrorResumeNext { Single.just(emptyList()) }
    }
}