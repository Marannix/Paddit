package com.example.paddit.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.paddit.model.PostResponse
import com.example.paddit.usecase.PostUseCase
import com.example.paddit.usecase.UserUseCase
import com.example.paddit.utils.RxImmediateSchedulerRule
import com.example.paddit.utils.UnitTestUtils
import com.google.gson.GsonBuilder
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*

class PostViewModelTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var postResponse: List<PostResponse>
    private val postUseCase = mock(PostUseCase::class.java)
    private val userUseCase = mock(UserUseCase::class.java)
    private val observer: Observer<PostViewModel.ViewState> = mock(Observer::class.java) as Observer<PostViewModel.ViewState>
    private val captor = ArgumentCaptor.forClass(PostViewModel.ViewState::class.java)
    private lateinit var viewModel: PostViewModel

    @Before
    fun setUp() {
        val response = UnitTestUtils.readJsonFile("post.json")
        postResponse = GsonBuilder().create().fromJson(response, Array<PostResponse>::class.java).toList()
        viewModel = PostViewModel(
            postUseCase = postUseCase,
            userUseCase = userUseCase
        )
        viewModel.getLiveData().observeForever(observer as Observer<in PostViewModel.ViewState>)
    }

    @Test
    fun `when start then emits Loading State`() {
        `when`(postUseCase.getPosts()).thenReturn(Single.never())
        `when`(userUseCase.getUsers()).thenReturn(Single.never())

        viewModel.start()

        captor.run {
            verify(observer, times(2)).onChanged(capture())
            assertEquals(PostViewModel.ViewState.Loading, value)
        }
    }

    @Test
    fun `given empty posts and user when fetching then emits Error state`() {
        `when`(postUseCase.getPosts()).thenReturn(Single.just(emptyList()))
        `when`(userUseCase.getUsers()).thenReturn(Single.just(emptyList()))

        viewModel.start()

        captor.run {
            verify(observer, times(3)).onChanged(capture())
            assertEquals(PostViewModel.ViewState.Error("Both Post and Users are Empty"), value)
        }
    }

}