package com.koidev.data.store.stackoverflow

import com.koidev.data.model.QuestionEntity
import com.koidev.data.repository.questions.StackOverFlowDataStore
import io.reactivex.Observable

class StackOverFlowCacheDataStore(

): StackOverFlowDataStore {

    override fun getQuestions(page: Int): Observable<List<QuestionEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}