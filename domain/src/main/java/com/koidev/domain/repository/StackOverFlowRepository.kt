package com.koidev.domain.repository

import com.koidev.domain.Question
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface StackOverFlowRepository {

    fun getQuestionsByPage(page: Int): Observable<List<Question>>

    fun getQuestionsByQuery(query: String): Single<List<Question>>

    fun saveQuestions(questions: ArrayList<Question>): Completable

    fun clearQuestions(): Completable
}