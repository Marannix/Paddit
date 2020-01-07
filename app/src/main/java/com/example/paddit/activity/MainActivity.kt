package com.example.paddit.activity

import android.os.Bundle
import android.util.Log
import com.example.paddit.api.PostApi
import com.example.paddit.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var postApi: PostApi
//    private val apiService = object : ApiService {}
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retrievePosts()
    }

    private fun retrievePosts() {
        val disposable = postApi.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("All good here chief", it[1].toString())
            }, {
                Log.d("This ain't it chief", it.message)
            })
        disposables.add(disposable)
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }

}
