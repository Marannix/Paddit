package com.example.paddit.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paddit.model.PostResponse
import com.example.paddit.repository.PostRepository
import com.example.paddit.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val disposables = CompositeDisposable()
    var posts: MutableLiveData<List<PostResponse>> = MutableLiveData(emptyList())

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
                Log.d("All good here - User", it.username)
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