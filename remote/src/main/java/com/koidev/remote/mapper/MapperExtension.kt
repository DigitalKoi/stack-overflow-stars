package com.koidev.remote.mapper

import com.koidev.data.model.OwnerEntity
import com.koidev.data.model.QuestionEntity
import com.koidev.remote.model.Owner
import com.koidev.remote.model.QuestionResponse

fun QuestionResponse.toEntity(): List<QuestionEntity> = items.map { item ->
    QuestionEntity(
        tags = item.tags,
        owner = item.owner.toEntity(),
        isAnswered = item.isAnswered,
        viewCount = item.viewCount,
        answerCount = item.answerCount,
        score = item.score,
        lastActivityDate = item.lastActivityDate,
        creationDate = item.creationDate,
        questionId = item.questionId,
        questionLink = item.questionLink,
        title = item.title
    )
}

private fun Owner.toEntity() = OwnerEntity(
    reputation = reputation,
    userId = userId,
    userType = userType,
    profileImage = profileImage,
    displayName = displayName,
    profileLink = profileLink
)