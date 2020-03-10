package com.koidev.persistence.implementation

import com.koidev.data.model.QuestionEntity
import com.koidev.data.repository.questions.StackOverFlowCache
import com.koidev.persistence.StackOverFlowDatabase
import com.koidev.persistence.mapper.toCache
import com.koidev.persistence.mapper.toQuestionEntity
import com.koidev.persistence.model.CachedQuestions
import io.reactivex.Completable
import io.reactivex.Maybe

class DefaultStackOverFlowCache(
    private val db: StackOverFlowDatabase
) : StackOverFlowCache {

    override fun saveQuestions(result: List<QuestionEntity>): Completable =
        Completable.defer {
            result.forEach { question ->
                db.questionsDao().insert(question.toCache())
            }
            Completable.complete()
        }

    override fun getQuestions(query: String): Maybe<List<QuestionEntity>> = db
        .questionsDao()
        .getQuestions(
            //TODO: add search query here
        )
        .map { it.map(CachedQuestions::toQuestionEntity) }

    override fun clear(): Completable = Completable
        .defer {
            db.questionsDao().clear()
            Completable.complete()
        }
}