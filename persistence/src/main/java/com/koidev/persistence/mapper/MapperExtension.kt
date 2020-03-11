package com.koidev.persistence.mapper

import com.koidev.data.model.OwnerEntity
import com.koidev.data.model.QuestionEntity
import com.koidev.persistence.model.CachedOwner
import com.koidev.persistence.model.CachedQuestions

fun QuestionEntity.toCache(): CachedQuestions =
    CachedQuestions(
        questionId = questionId,
        tags = tags,
        owner = owner.toCache(),
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
        owner = owner.toEntity(),
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

private fun OwnerEntity.toCache() = CachedOwner(
    userId = userId,
    reputation = reputation,
    userType = userType,
    profileImage = profileImage,
    displayName = displayName,
    profileLink = profileLink
)

private fun CachedOwner.toEntity() = OwnerEntity(
    userId = userId,
    reputation = reputation,
    userType = userType,
    profileImage = profileImage,
    displayName = displayName,
    profileLink = profileLink
)