package com.koidev.data.questions

import com.koidev.data.mapper.toDomain
import com.koidev.data.model.QuestionEntity
import com.koidev.domain.repository.StackOverFlowRepository

class DefaultStackOverFlowRepository(
    private val stackOverFlowRemote: StackOverFlowRemote
): StackOverFlowRepository {

    override fun getQuestions(page: Int) = stackOverFlowRemote
        .getQuestions(page)
        .map { questions ->
            questions.map(QuestionEntity::toDomain)
        }
}