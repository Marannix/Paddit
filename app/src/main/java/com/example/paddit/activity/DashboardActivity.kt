package com.example.paddit.activity

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.paddit.R
import com.example.paddit.dialog.FullscreenLoadingDialog
import com.example.paddit.fragment.DashboardFragment
import com.example.paddit.model.PostResponse
import com.example.paddit.model.UserResponse
import com.example.paddit.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_layout.*

class DashboardActivity : BaseActivity() {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadingDialog = FullscreenLoadingDialog(this).apply {
            setCanceledOnTouchOutside(false)
        }
        viewModel = this.let { ViewModelProviders.of(it, viewModelFactory).get(DashboardViewModel::class.java) }
        viewModel.start()
        viewModel.getLiveData().observe(this, Observer {
            when (it) {
                DashboardViewModel.ViewState.Empty -> {
                    loadingDialog.hide()
                }
                DashboardViewModel.ViewState.Loading -> {
                    loadingDialog.show()
                    showErrorLayout(false)
                }
                is DashboardViewModel.ViewState.Content -> {
                    loadingDialog.hide()
                    initVenueFragment(it.post, it.users)
                    showErrorLayout(false)
                }
                is DashboardViewModel.ViewState.Error -> {
                    loadingDialog.hide()
                    showErrorLayout(true)
                }
            }
        })
    }

    private fun showErrorLayout(show: Boolean) {
        when {
            show -> {
                errorLayout.visibility = View.VISIBLE
                restartButton.setOnClickListener {
                    viewModel.start()
                }
            }
            else -> errorLayout.visibility = View.INVISIBLE
        }

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

    // Added due to android.view.WindowLeaked
    // When I close the activity the loading progress is triggered which is odd
    override fun onPause() {
        loadingDialog.dismiss()
        super.onPause()
    }
}
