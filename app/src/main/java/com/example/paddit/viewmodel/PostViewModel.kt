package com.example.paddit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paddit.model.PostResponse
import com.example.paddit.model.UsersModel
import com.example.paddit.model.UserResponse
import com.example.paddit.repository.PostRepository
import com.example.paddit.repository.UserRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction

import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val disposables = CompositeDisposable()
    var posts: MutableLiveData<List<PostResponse>> = MutableLiveData(emptyList())

    fun stuff(): Observable<UsersModel> {
        return Observable.zip(postRepository.getPosts().toObservable(), userRepository.getUsers().toObservable(),
            BiFunction { post: List<PostResponse>, user: List<UserResponse> -> UsersModel(post, user) }
        )
    }

    fun getStuff() {
        val disposable = stuff().observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("All good here - Post", it.posts[1].userId.toString())
                Log.d("All good here - User", it.user[1].username)
            }, {
                Log.d("This ain't it chief", it.message)
            })

        disposables.add(disposable)
    }

    fun getPosts() {
        val disposable = postRepository.getPosts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                posts.value = it
                Log.d("All good here - Post", it[1].toString())
            }, {
                Log.d("This ain't it chief", it.message)
            })

        disposables.add(disposable)
    }

    fun getUsers() {
        val disposable = userRepository.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("All good here - User", it[1].username)
            }, {
                Log.d("This ain't it chief", it.message)
            })

        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}