package com.example.paddit.repository

import com.example.paddit.api.UserApi
import com.example.paddit.data.UserDao
import com.example.paddit.model.UserResponse
import com.example.paddit.utils.UnitTestUtils
import com.google.gson.GsonBuilder
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class UserRepositoryTest {

    private val api = Mockito.mock(UserApi::class.java)
    private val dao = Mockito.mock(UserDao::class.java)

    private lateinit var userResponse: List<UserResponse>

    private val userRepository by lazy { UserRepository(api, dao) }

    @Before
    fun setUp() {
        val response = UnitTestUtils.readJsonFile("users.json")
        userResponse = GsonBuilder().create().fromJson(response, Array<UserResponse>::class.java).toList()
    }

    @Test
    fun `when calling the network, parse correctly the response and retrieve users`() {
        Mockito.`when`(api.getUsers()).thenReturn(Single.just(userResponse))
        Mockito.`when`(dao.getUsers()).thenReturn(Single.just(userResponse))

        userRepository.getUsers()
            .test()
            .assertValue(userResponse)
            .assertComplete()
    }

    @Test
    fun `when calling the network and fails, parse correctly the response from database`() {
        Mockito.`when`(api.getUsers()).thenReturn(Single.just(emptyList()))
        Mockito.`when`(dao.getUsers()).thenReturn(Single.just(userResponse))
        userRepository.getUsers()
            .test()
            .assertValue(userResponse)
            .assertComplete()
    }

}