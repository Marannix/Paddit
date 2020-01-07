package com.example.paddit.activity

import android.os.Bundle
import android.util.Log
import com.example.paddit.api.PostApi
import com.example.paddit.R
import com.example.paddit.repository.PostRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class DashboardActivity : BaseActivity() {

    @Inject
    lateinit var postRepository: PostRepository
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retrievePosts()
    }

    private fun retrievePosts() {
        val disposable = postRepository.getPosts()
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
