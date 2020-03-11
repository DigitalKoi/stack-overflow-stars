package com.koidev.persistence.implementation

import com.koidev.data.model.QuestionEntity
import com.koidev.data.repository.questions.StackOverFlowCache
import com.koidev.persistence.StackOverFlowDatabase
import com.koidev.persistence.mapper.toCache
import com.koidev.persistence.mapper.toQuestionEntity
import com.koidev.persistence.model.CachedQuestions
import io.reactivex.Completable
import io.reactivex.Single

class DefaultStackOverFlowCache(
    private val db: StackOverFlowDatabase
) : StackOverFlowCache {

    override fun saveQuestions(result: List<QuestionEntity>): Completable =
        Completable.defer {

            val questions = result.map { it.toCache() }
            db.questionsDao().insert(questions)

            Completable.complete()
        }

    override fun getQuestions(): Single<List<QuestionEntity>> = db
        .questionsDao()
        .getQuestions()
        .map { it.map(CachedQuestions::toQuestionEntity) }

    override fun clear(): Completable = Completable
        .defer {
            db.questionsDao().clear()
            Completable.complete()
        }
}