package com.koidev.domain.interactor

import com.koidev.domain.Question
import com.koidev.domain.executor.PostExecutionThread
import com.koidev.domain.interactor.base.SingleUseCase
import com.koidev.domain.repository.StackOverFlowRepository
import io.reactivex.Single

class GetQuestionsList(
    postExecutionThread: PostExecutionThread,
    private val stackOverFlowRepository: StackOverFlowRepository
): SingleUseCase<List<Question>, Int?>(postExecutionThread)  {

    override fun buildUseCaseObservable(params: Int?): Single<List<Question>> {

        if (params == null) throw IllegalArgumentException("Argument can't be null")

        return stackOverFlowRepository
            .getQuestionsByPage(params)
            // TODO: cache
    }
}