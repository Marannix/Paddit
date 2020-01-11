package com.example.paddit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paddit.R
import com.example.paddit.model.PostResponse
import com.example.paddit.model.UserResponse
import kotlinx.android.synthetic.main.dashboard_items.view.*

class DashboardAdapter : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    private var posts: List<PostResponse> = emptyList()
    private var users: List<UserResponse> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.dashboard_items, parent, false))
    }

    override fun getItemCount(): Int {
        return if (posts.size > 10) {
            10
        } else {
            posts.size
        }
    }

    fun setData(posts: List<PostResponse>, users: List<UserResponse>) {
        this.posts = posts
        this.users = users
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (posts.isNotEmpty()) {
            holder.bind(posts[position], users)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(
            posts: PostResponse,
            users: List<UserResponse>
        ) {

           for (i in 0..users.size) {
               if (users[i].id == posts.userId) {
                   itemView.username.text = users[i].username
                   break
               }
           }
            itemView.title.text = posts.title
            itemView.body.text = posts.body
        }
    }

}