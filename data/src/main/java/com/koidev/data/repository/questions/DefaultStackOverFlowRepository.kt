package com.koidev.data.repository.questions

import com.koidev.data.mapper.toDomain
import com.koidev.data.model.QuestionEntity
import com.koidev.data.store.stackoverflow.StackOverFlowFactory
import com.koidev.domain.repository.StackOverFlowRepository

class DefaultStackOverFlowRepository(
    private val factory: StackOverFlowFactory
): StackOverFlowRepository {

    override fun getQuestions(page: Int) = factory
        .getStackOverFlowRemoteDataStore()
        .getQuestions(page)
        .map { questions ->
            questions.map(QuestionEntity::toDomain)
        }
}