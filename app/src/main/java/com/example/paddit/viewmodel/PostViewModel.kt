package com.example.paddit.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.paddit.repository.PostRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val disposables = CompositeDisposable()

    fun getPosts() {
        val disposable = postRepository.getPosts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("All good here chief", it[1].toString())
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