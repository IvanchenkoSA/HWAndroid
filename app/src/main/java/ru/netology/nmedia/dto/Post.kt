package ru.netology.nmedia.dto

import java.text.DecimalFormat

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val likeByMe: Boolean = true,
    val likes: Int = 0,
    val shares: Int = 0
)

