package com.koidev.data.repository.questions

import com.koidev.data.model.QuestionEntity
import io.reactivex.Observable

interface StackOverFlowDataStore {

    fun getQuestions(page: Int): Observable<List<QuestionEntity>>
}