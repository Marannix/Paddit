package com.example.paddit.activity

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.paddit.R
import com.example.paddit.dialog.FullscreenLoadingDialog
import com.example.paddit.fragment.DashboardFragment
import com.example.paddit.model.PostResponse
import com.example.paddit.model.UserResponse
import com.example.paddit.viewmodel.PostViewModel

class DashboardActivity : BaseActivity() {

    private lateinit var viewModel: PostViewModel
    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadingDialog = FullscreenLoadingDialog(this).apply {
            setCanceledOnTouchOutside(false)
        }
        viewModel = this.let { ViewModelProviders.of(it, viewModelFactory).get(PostViewModel::class.java) }
        viewModel.start()

        viewModel.livedata.observe(this, Observer {
            when (it) {
                //Remove empty
                PostViewModel.ViewState.Empty -> {
                    loadingDialog.hide()
                    Log.d("look chief", "I'm empty")
                }
                PostViewModel.ViewState.Loading -> {
                    loadingDialog.show()
                    Log.d("look chief", "I'm loading")
                }
                is PostViewModel.ViewState.Content -> {
                    loadingDialog.hide()
                    initVenueFragment(it.post, it.users)
                    Log.d("look chief - post", it.post.toString())
                    Log.d("look chief - user", it.users.toString())
                }
                is PostViewModel.ViewState.Error -> {
                    loadingDialog.hide()
                    Log.d("look chief error", it.error)
                }
            }
        })
    }

    private fun initVenueFragment(
        posts: List<PostResponse>,
        users: List<UserResponse>
    ) {

        supportFragmentManager.createFragment(
            fragmentId = R.id.fragmentContainer,
            factory = { DashboardFragment() }
        ).display(posts, users)
    }

}
