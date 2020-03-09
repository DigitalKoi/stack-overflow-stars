package com.koidev.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.koidev.persistence.constant.QuestionConstants

@Entity(tableName = QuestionConstants.QUESTIONS_TABLE_NAME)
data class CachedQuestions(
    @PrimaryKey val questionId: Long,
    val tags: List<String>,
//    val owner: OwnerEntity,
    val isAnswered: Boolean,
    val viewCount: Long,
    val answerCount: Long,
    val score: Long,
    val lastActivityDate: Long,
    val creationDate: Long,
    val questionLink: String,
    val title: String
)