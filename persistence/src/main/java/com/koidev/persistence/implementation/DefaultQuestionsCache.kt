package com.koidev.persistence.implementation

import com.koidev.data.model.QuestionEntity
import com.koidev.persistence.StackOverFlowDatabase
import com.koidev.persistence.mapper.toCache
import com.koidev.persistence.mapper.toQuestionEntity
import com.koidev.persistence.model.CachedQuestions
import io.reactivex.Completable
import io.reactivex.Single

class DefaultQuestionsCache(private val db: StackOverFlowDatabase) {

    fun saveQuestions(result: List<QuestionEntity>): Completable =
        Completable.defer {
            result.forEach { question ->
                db.questionsDao().insert(question.toCache())
            }
            Completable.complete()
        }

    fun getQuestions(): Single<List<QuestionEntity>> = db
        .questionsDao()
        .getQuestions()
        .map { it.map(CachedQuestions::toQuestionEntity) }

    fun clear(): Completable = Completable
        .defer {
            db.questionsDao().clear()
            Completable.complete()
        }
}