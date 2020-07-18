package ru.netology.dto

import java.util.Date

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    var created: Date,
    var likes: Int = 0,
    var comments: Int = 0,
    var shares: Int = 0,
    var likedByMe: Boolean = false,
    var commentedByMe: Boolean = false,
    var sharedByMe: Boolean = false
)