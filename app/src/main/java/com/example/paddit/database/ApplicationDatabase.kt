package com.example.paddit.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.paddit.data.PostsDao
import com.example.paddit.data.UserDao
import com.example.paddit.model.PostResponse
import com.example.paddit.model.UserResponse

/**
 * Database for the application
 */
@Database(
    entities = [PostResponse::class, UserResponse::class],
    version = 1
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun postsDao(): PostsDao
    abstract fun usersDao(): UserDao

}