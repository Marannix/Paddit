package com.example.paddit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paddit.R
import com.example.paddit.adapter.DashboardAdapter
import com.example.paddit.model.PostResponse
import com.example.paddit.model.UserResponse
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : BaseFragment() {

    private val adapter = DashboardAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createAdapter()
    }

    fun display(
        posts: List<PostResponse>,
        users: List<UserResponse>
    ) {
        adapter.setData(posts, users)
    }

    private fun createAdapter() {
        dashboardRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        dashboardRecyclerView.adapter = adapter
    }

}
