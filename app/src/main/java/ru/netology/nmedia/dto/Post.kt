package ru.netology.nmedia.dto

import java.text.DecimalFormat

data class Post (
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    var likeByMe: Boolean = false,
    var likes: Int = 0,
    var shares: Int = 0
)

