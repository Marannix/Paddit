package com.example.paddit.state

import com.example.paddit.model.UserResponse

sealed class UserDataState {
    class Success(val users: List<UserResponse>) : UserDataState()
    class GenericError(val errorMessage: String?) : UserDataState()
}