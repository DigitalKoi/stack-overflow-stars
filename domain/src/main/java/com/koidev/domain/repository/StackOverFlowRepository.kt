package com.koidev.domain.repository

import com.koidev.domain.Question
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface StackOverFlowRepository {

    fun getQuestionsByPage(page: Int): Single<List<Question>>

    fun getQuestionsByQuery(query: String): Maybe<List<Question>>

    fun saveQuestions(questions: ArrayList<Question>): Completable

    fun clearQuestions(): Completable
}