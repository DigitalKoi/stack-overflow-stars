package com.koidev.data.store.stackoverflow

import com.koidev.data.model.QuestionEntity
import com.koidev.data.repository.questions.StackOverFlowDataStore
import com.koidev.data.repository.questions.StackOverFlowRemote
import io.reactivex.Observable

class StackOverFlowRemoteDataStore (
    private val remote: StackOverFlowRemote
): StackOverFlowDataStore {

    override fun getQuestions(page: Int): Observable<List<QuestionEntity>> =
        remote.getQuestions(page)

}