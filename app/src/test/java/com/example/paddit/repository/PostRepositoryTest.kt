package com.example.paddit.repository

import com.example.paddit.api.PostApi
import com.example.paddit.data.PostsDao
import com.example.paddit.model.PostResponse
import com.example.paddit.utils.UnitTestUtils.Companion.readJsonFile
import com.google.gson.GsonBuilder
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class PostRepositoryTest {

    private val api = Mockito.mock(PostApi::class.java)
    private val dao = Mockito.mock(PostsDao::class.java)

    private lateinit var postResponse: List<PostResponse>

    private val postRepository by lazy { PostRepository(api, dao) }

    @Before
    fun setUp() {
        val response = readJsonFile("post.json")
        postResponse = GsonBuilder().create().fromJson(response, Array<PostResponse>::class.java).toList()
    }

    @Test
    fun `when calling the network, parse correctly the response and retrieve posts`() {
        Mockito.`when`(api.getPosts()).thenReturn(Single.just(postResponse))
        Mockito.`when`(dao.getPosts()).thenReturn(Single.just(postResponse))

        postRepository.getPosts()
            .test()
            .assertValue(postResponse)
            .assertComplete()
    }

    @Test
    fun `when calling the network and fails, parse correctly the response from database`() {
        Mockito.`when`(api.getPosts()).thenReturn(Single.just(emptyList()))
        Mockito.`when`(dao.getPosts()).thenReturn(Single.just(postResponse))

        postRepository.getPosts()
            .test()
            .assertValue(postResponse)
            .assertComplete()
    }

}