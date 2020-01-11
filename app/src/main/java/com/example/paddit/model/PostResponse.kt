package com.example.paddit.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)
