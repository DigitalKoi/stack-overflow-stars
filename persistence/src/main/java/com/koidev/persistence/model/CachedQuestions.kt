package com.koidev.persistence.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.koidev.persistence.constant.QuestionConstants
import com.koidev.persistence.utils.Converters

@Entity(
    tableName = QuestionConstants.QUESTIONS_TABLE_NAME
)
data class CachedQuestions(
    @PrimaryKey val questionId: Long,
    @TypeConverters(Converters::class)
    val tags: List<String>,
    @Embedded val owner: CachedOwner,
    val isAnswered: Boolean,
    val viewCount: Long,
    val answerCount: Long,
    val score: Long,
    val lastActivityDate: Long,
    val creationDate: Long,
    val questionLink: String,
    val title: String
)