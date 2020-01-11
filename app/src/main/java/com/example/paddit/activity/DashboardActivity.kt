package com.example.paddit.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.paddit.R
import com.example.paddit.fragment.DashboardFragment
import com.example.paddit.repository.PostRepository
import com.example.paddit.viewmodel.PostViewModel
import javax.inject.Inject

class DashboardActivity : BaseActivity() {

    @Inject
    lateinit var postRepository: PostRepository

    private lateinit var viewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = this.let { ViewModelProviders.of(it, viewModelFactory).get(PostViewModel::class.java) }
        initVenueFragment()
    }


    private fun initVenueFragment() {
        val fragment = DashboardFragment.newInstance()
        supportFragmentManager.inTransaction {
            add(R.id.fragmentContainer, fragment).addToBackStack(fragment.javaClass.name)
        }
    }


//    private fun retrievePosts() {
//        viewModel.start()
//        viewModel.livedata.observe(this, Observer {
//            when (it) {
//                //Remove empty
//                PostViewModel.ViewState.Empty -> {
//                    Log.d("look chief", "I'm empty")
//                }
//                PostViewModel.ViewState.Loading -> {
//                    Log.d("look chief", "I'm loading")
//                }
//                is PostViewModel.ViewState.Content -> {
//                    Log.d("look chief - post", it.post.toString())
//                    Log.d("look chief - user", it.users.toString())
//                }
//                is PostViewModel.ViewState.Error -> {
//                    Log.d("look chief error", it.error)
//                }
//            }
//        })
//
//    }

}
