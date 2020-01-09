package com.example.paddit.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserResponse(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val username: String
)