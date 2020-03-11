package com.koidev.data.repository.questions

import com.koidev.data.mapper.toDomain
import com.koidev.data.mapper.toEntity
import com.koidev.data.model.QuestionEntity
import com.koidev.domain.Question
import com.koidev.domain.repository.StackOverFlowRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class DefaultStackOverFlowRepository(
    private val factory: StackOverFlowFactory
): StackOverFlowRepository {

    override fun getQuestionsByPage(page: Int): Observable<List<Question>> = factory
        .getStackOverFlowRemote()
        .getQuestions(page)
        .map { it.map(QuestionEntity::toDomain) }

    override fun getQuestionsByQuery(query: String): Single<List<Question>> = factory
        .getStackOverFlowCache()
        .getQuestions(query)
        .map { it.map(QuestionEntity::toDomain) }

    override fun saveQuestions(questions: ArrayList<Question>): Completable = factory
        .getStackOverFlowCache()
        .saveQuestions(questions.map(Question::toEntity))

    override fun clearQuestions(): Completable = factory
        .getStackOverFlowCache().clear()
}