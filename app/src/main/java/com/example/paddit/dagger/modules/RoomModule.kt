package com.example.paddit.dagger.modules

import android.app.Application
import androidx.room.Room
import com.example.paddit.data.PostsDao
import com.example.paddit.data.UserDao
import com.example.paddit.database.ApplicationDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Modules for Room Database
 */
@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(application: Application): ApplicationDatabase {
        return Room.databaseBuilder(
            application,
            ApplicationDatabase::class.java, "posts.db"
        )
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun providesPostsDao(applicationDatabase: ApplicationDatabase): PostsDao {
        return applicationDatabase.postsDao()
    }

    @Singleton
    @Provides
    fun providesUsersDao(applicationDatabase: ApplicationDatabase): UserDao {
        return applicationDatabase.usersDao()
    }
}