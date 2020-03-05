package com.koidev.domain.repository

import com.koidev.domain.Question
import io.reactivex.Observable

interface StackOverFlowRepository {

    fun getQuestions(page: Int): Observable<List<Question>>
}