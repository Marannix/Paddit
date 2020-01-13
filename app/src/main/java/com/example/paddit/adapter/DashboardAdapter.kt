package com.example.paddit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paddit.R
import com.example.paddit.model.PostResponse
import com.example.paddit.model.UserResponse
import kotlinx.android.synthetic.main.dashboard_items.view.*

private const val MAX_SIZE = 10

class DashboardAdapter : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    private var posts: List<PostResponse> = emptyList()
    private var users: List<UserResponse> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.dashboard_items, parent, false))
    }

    override fun getItemCount(): Int {
        // As required in the test, display a maximum of 10 posts
        return if (posts.size > MAX_SIZE) {
            MAX_SIZE
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
        // Safeguard, not really required since the adapter is only being created when there are post available
        if (posts.isNotEmpty()) {
            holder.bind(posts[position], users)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(
            posts: PostResponse,
            users: List<UserResponse>
        ) {

            // Compare post userid and users id to find the correct username
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