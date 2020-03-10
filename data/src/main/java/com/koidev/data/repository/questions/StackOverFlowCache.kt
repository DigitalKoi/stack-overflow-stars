package com.koidev.data.repository.questions

import com.koidev.data.model.QuestionEntity
import io.reactivex.Completable
import io.reactivex.Single

interface StackOverFlowCache {

    fun saveQuestions(list: List<QuestionEntity>): Completable

    fun getQuestions(query: String): Single<List<QuestionEntity>>

    fun clear(): Completable
}