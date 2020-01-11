package com.example.paddit.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.paddit.R
import com.example.paddit.model.PostResponse
import com.example.paddit.model.UserResponse
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = DashboardFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("created", "view created")
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    fun display(
        posts: List<PostResponse>,
        users: List<UserResponse>
    ) {
        textview.text = posts[4].userId.toString()
    }

}
