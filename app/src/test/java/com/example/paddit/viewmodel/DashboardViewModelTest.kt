package com.example.paddit.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.paddit.model.PostResponse
import com.example.paddit.model.UserResponse
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

class DashboardViewModelTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()


    // Allow me to run LiveData synchronously.
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var postResponse: List<PostResponse>
    private lateinit var userResponse: List<UserResponse>
    private lateinit var viewModel: DashboardViewModel

    private val postUseCase = mock(PostUseCase::class.java)
    private val userUseCase = mock(UserUseCase::class.java)
    private val observer: Observer<DashboardViewModel.ViewState> = mock(Observer::class.java) as Observer<DashboardViewModel.ViewState>
    private val captor = ArgumentCaptor.forClass(DashboardViewModel.ViewState::class.java)


    @Before
    fun setUp() {
        val postResponseJsonFile = UnitTestUtils.readJsonFile("post.json")
        val userResponseJsonFile = UnitTestUtils.readJsonFile("users.json")
        postResponse = GsonBuilder().create().fromJson(postResponseJsonFile, Array<PostResponse>::class.java).toList()
        userResponse = GsonBuilder().create().fromJson(userResponseJsonFile, Array<UserResponse>::class.java).toList()
        viewModel = DashboardViewModel(
            postUseCase = postUseCase,
            userUseCase = userUseCase
        )
        viewModel.getLiveData().observeForever(observer as Observer<in DashboardViewModel.ViewState>)
    }

    @Test
    fun `when start then emits Loading State`() {
        `when`(postUseCase.getPosts()).thenReturn(Single.never())
        `when`(userUseCase.getUsers()).thenReturn(Single.never())

        viewModel.start()

        captor.run {
            verify(observer, times(2)).onChanged(capture())
            assertEquals(DashboardViewModel.ViewState.Loading, value)
        }
    }

    @Test
    fun `when posts and users fetched then emits Content state`() {
        `when`(postUseCase.getPosts()).thenReturn(Single.just(postResponse))
        `when`(userUseCase.getUsers()).thenReturn(Single.just(userResponse))

        viewModel.start()

        // capture() captures the argument when the onChanged is called on the observer

        captor.run {
            verify(observer, times(3)).onChanged(capture())
            assertEquals(DashboardViewModel.ViewState.Content(postResponse, userResponse), value)
        }
    }

    @Test
    fun `given empty posts and user when fetching then emits Error state`() {
        `when`(postUseCase.getPosts()).thenReturn(Single.just(emptyList()))
        `when`(userUseCase.getUsers()).thenReturn(Single.just(emptyList()))

        viewModel.start()

        captor.run {
            verify(observer, times(3)).onChanged(capture())
            assertEquals(DashboardViewModel.ViewState.Error("Both Post and Users are Empty"), value)
        }
    }

    /**
     * I added captor as a way to test the observable
     * https://dev.to/arthlimchiu/how-to-unit-test-livedata-and-viewmodel-5h7f
     */
}