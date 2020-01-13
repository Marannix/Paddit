package com.example.paddit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paddit.model.PostResponse
import com.example.paddit.model.UserResponse
import com.example.paddit.usecase.PostUseCase
import com.example.paddit.usecase.UserUseCase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Singles
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val postUseCase: PostUseCase,
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val livedata = MutableLiveData<ViewState>().apply {
        value = ViewState.Empty
    }

    private val disposables = CompositeDisposable()

    fun getLiveData(): LiveData<ViewState> {
        return livedata
    }

    fun start() {
        disposables.add(getInformation())
    }

    private fun getInformation(): Disposable {
        return Singles.zip(userUseCase.getUsers(), postUseCase.getPosts()) { user, posts ->
            createViewState(Event.Success(posts, user))
        }.observeOn(AndroidSchedulers.mainThread())
            .toObservable()
            .startWith(ViewState.Loading)
            .onErrorResumeNext { throwable: Throwable -> Observable.just(createViewState(Event.Error(throwable.message))) }
            .subscribe { viewstate ->
                livedata.value = viewstate
            }
    }


    private fun createViewState(event: Event): ViewState {
        return when (event) {
            is Event.Success -> {
                //TODO: Fix hacky way of handling error
                if (event.post.isNullOrEmpty() && event.users.isNullOrEmpty()) {
                    // This should be extracted to a String however ViewModel shouldn't have reference to the context
                   // Not the best way to handle this
                    ViewState.Error("Both Post and Users are Empty")
                } else {
                    ViewState.Content(event.post, event.users)
                }
            }
            is Event.Error -> {
                ViewState.Error(event.error)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    sealed class Event {
        data class Success(val post: List<PostResponse>, val users: List<UserResponse>) : Event()
        data class Error(val error: String?) : Event()
    }

    sealed class ViewState {
        object Empty : ViewState()
        object Loading : ViewState()
        data class Content(val post: List<PostResponse>, val users: List<UserResponse>) : ViewState()
        data class Error(val error: String?) : ViewState()
    }
}