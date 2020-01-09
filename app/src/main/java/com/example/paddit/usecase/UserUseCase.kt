package com.example.paddit.usecase

import com.example.paddit.model.UserResponse
import com.example.paddit.repository.UserRepository
import com.example.paddit.state.UserDataState
import io.reactivex.Single
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    fun getUsers(): Single<UserDataState> {
        return userRepository.getUsers()
            .map<UserDataState> { listOfUsers ->
                UserDataState.Success(listOfUsers)
            }.onErrorReturn {
                UserDataState.GenericError(it.message)
            }
    }
}