package com.koidev.domain

data class Question(
    val tags: List<String>,
    val owner: Owner,
    val isAnswered: Boolean,
    val viewCount: Long,
    val answerCount: Long,
    val score: Long,
    val lastActivityDate: Long,
    val creationDate: Long,
    val questionId: Long,
    val questionLink: String,
    val title: String
)

data class Owner(
    val reputation: Long,
    val userId: Long,
    val userType: String,
    val profileImage: String,
    val displayName: String,
    val profileLink: String
)