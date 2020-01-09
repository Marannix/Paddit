package com.example.paddit.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.paddit.repository.UserRepository
import com.example.paddit.state.PostDataState
import com.example.paddit.state.UserDataState
import com.example.paddit.usecase.PostUseCase
import com.example.paddit.usecase.UserUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val postUseCase: PostUseCase,
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()
//    var posts: MutableLiveData<List<PostResponse>> = MutableLiveData(emptyList())
//
//    fun stuff(): Observable<UsersModel> {
//        return Observable.zip(postRepository.getPosts().toObservable(), userRepository.getUsers().toObservable(),
//            BiFunction { post: List<PostResponse>, user: List<UserResponse> -> UsersModel(post, user) }
//        )
//    }
//
//    fun getStuff() {
//        val disposable = stuff().observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Log.d("All good here - Post", it.posts[1].userId.toString())
//                Log.d("All good here - User", it.user[1].username)
//            }, {
//                Log.d("This ain't it chief", it.message)
//            })
//
//        disposables.add(disposable)
//    }

    fun getPosts() {
        val disposable = postUseCase.getPosts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { postDataState ->
                when (postDataState) {
                    is PostDataState.Success -> {
                        Log.d("All good here - Post", postDataState.posts[1].toString())
                    }
                    is PostDataState.GenericError -> {
                        Log.d("This ain't it chief", postDataState.errorMessage)
                    }
                }
            }

        disposables.add(disposable)
    }

    fun getUsers() {
//        val disposable = userRepository.getUsers()
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Log.d("All good here - User", it[1].username)
//            }, {
//                Log.d("This ain't it chief", it.message)
//            })

        val disposable = userUseCase.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { userDataState ->
                when (userDataState) {
                    is UserDataState.Success -> {
                        Log.d("All good here - User", userDataState.users[1].toString())
                    }
                    is UserDataState.GenericError -> {
                        Log.d("This ain't it chief", userDataState.errorMessage)
                    }
                }
            }

        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}