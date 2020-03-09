package com.koidev.data.mapper

import com.koidev.data.model.OwnerEntity
import com.koidev.data.model.QuestionEntity
import com.koidev.domain.Owner
import com.koidev.domain.Question

fun QuestionEntity.toDomain() = Question(
    tags = tags,
    owner = owner?.toDomain(),
    isAnswered = isAnswered,
    viewCount = viewCount,
    answerCount = answerCount,
    score = score,
    lastActivityDate = lastActivityDate,
    creationDate = creationDate,
    questionId = questionId,
    questionLink = questionLink,
    title = title
)

private fun OwnerEntity.toDomain() = Owner(
    reputation = reputation,
    userType = userType,
    userId = userId,
    profileLink = profileLink,
    profileImage = profileImage,
    displayName = displayName
)