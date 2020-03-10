package com.koidev.remote.implementations

import com.koidev.data.model.QuestionEntity
import com.koidev.data.repository.questions.StackOverFlowRemote
import com.koidev.remote.mapper.toEntity
import com.koidev.remote.model.QuestionResponse
import com.koidev.remote.service.api.StackOverFlowApi
import io.reactivex.Single


class DefaultStackOverFlowRemote(
    private val api: StackOverFlowApi
): StackOverFlowRemote {

    override fun getQuestions(page: Int): Single<List<QuestionEntity>> = api
        .getQuestions(page)
        .map(QuestionResponse::toEntity)
}