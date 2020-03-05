package com.example.remote.model

import com.google.gson.annotations.SerializedName

data class QuestionResponse(
    @SerializedName("items")
    val items: List<Item>
)

data class Item(
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("owner")
    val owner: Owner,
    @SerializedName("is_answered")
    val isAnswered: Boolean,
    @SerializedName("view_count")
    val viewCount: Long,
    @SerializedName("answer_count")
    val answerCount: Long,
    @SerializedName("profile_image")
    val profileImage: String,
    @SerializedName("score")
    val score: Long,
    @SerializedName("last_activity_date")
    val lastActivityDate: Long,
    @SerializedName("creation_date")
    val creationDate: Long,
    @SerializedName("question_id")
    val questionId: Long,
    @SerializedName("link")
    val questionLink: String,
    @SerializedName("title")
    val title: String
)

data class Owner(
    @SerializedName("reputation")
    val reputation: Long,
    @SerializedName("user_id")
    val userId: Long,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("profile_image")
    val profileImage: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("link")
    val profileLink: String
)
