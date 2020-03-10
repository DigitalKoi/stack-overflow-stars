package com.koidev.data.repository.questions

import com.koidev.data.model.QuestionEntity
import io.reactivex.Single

interface StackOverFlowRemote {

    fun getQuestions(page: Int): Single<List<QuestionEntity>>
}