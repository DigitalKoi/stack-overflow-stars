package com.koidev.domain.interactor

import com.koidev.domain.Question
import com.koidev.domain.executor.PostExecutionThread
import com.koidev.domain.interactor.base.ObservableUseCase
import com.koidev.domain.repository.StackOverFlowRepository
import io.reactivex.Observable

class GetQuestionsList(
    postExecutionThread: PostExecutionThread,
    private val stackOverFlowRepository: StackOverFlowRepository
): ObservableUseCase<List<Question>, Int?>(postExecutionThread)  {

    override fun buildUseCaseObservable(params: Int?): Observable<List<Question>> {

        if (params == null) throw IllegalArgumentException("Argument can't be null")

        return stackOverFlowRepository
            .getQuestions(params)
            // TODO: cache
    }
}