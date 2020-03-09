package com.koidev.persistence.mapper

import com.koidev.data.model.QuestionEntity
import com.koidev.persistence.model.CachedQuestions

fun QuestionEntity.toCache(): CachedQuestions =
    CachedQuestions(
        questionId = questionId,
        tags = tags,
        isAnswered = isAnswered,
        viewCount = viewCount,
        answerCount = answerCount,
        score = score,
        lastActivityDate = lastActivityDate,
        creationDate = creationDate,
        questionLink = questionLink,
        title = title
    )

fun CachedQuestions.toQuestionEntity(): QuestionEntity =
    QuestionEntity(
        questionId = questionId,
        owner = null,
        tags = tags,
        isAnswered = isAnswered,
        viewCount = viewCount,
        answerCount = answerCount,
        score = score,
        lastActivityDate = lastActivityDate,
        creationDate = creationDate,
        questionLink = questionLink,
        title = title
    )