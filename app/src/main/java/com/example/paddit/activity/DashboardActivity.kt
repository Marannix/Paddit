package com.example.paddit.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.paddit.R
import com.example.paddit.repository.PostRepository
import com.example.paddit.viewmodel.PostViewModel
import javax.inject.Inject

class DashboardActivity : BaseActivity() {

    @Inject
    lateinit var postRepository: PostRepository

    private lateinit var viewModel : PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = this.let { ViewModelProviders.of(it, viewModelFactory).get(PostViewModel::class.java) }
        retrievePosts()
    }


    private fun retrievePosts() {
        viewModel.getStuff()
    }
}
