package com.koidev.domain.repository

import com.koidev.domain.Question
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface StackOverFlowRepository {

    fun getQuestionsByPage(page: Int): Single<List<Question>>

    fun getQuestionsByQuery(query: String): Observable<List<Question>>

    fun saveQuestions(questions: List<Question>): Completable

    fun clearQuestions(): Completable
}