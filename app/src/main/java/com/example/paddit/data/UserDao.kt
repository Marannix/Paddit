package com.example.paddit.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paddit.model.UserResponse
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUsers(posts: List<UserResponse>)

    @Query("select * from users")
    fun getUsers(): Single<List<UserResponse>>
}