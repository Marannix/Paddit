package com.example.paddit.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paddit.model.PostResponse
import io.reactivex.Single

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPosts(posts: List<PostResponse>)

    @Query("select * from posts")
    fun getPosts(): Single<List<PostResponse>>
}