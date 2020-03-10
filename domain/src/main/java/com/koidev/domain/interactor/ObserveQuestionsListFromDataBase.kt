package com.koidev.domain.interactor

import com.koidev.domain.Question
import com.koidev.domain.executor.PostExecutionThread
import com.koidev.domain.interactor.base.SingleUseCase
import com.koidev.domain.repository.StackOverFlowRepository
import io.reactivex.Single

class ObserveQuestionsListFromDataBase(
    postExecutionThread: PostExecutionThread,
    private val stackOverFlowRepository: StackOverFlowRepository
): SingleUseCase<List<Question>, String?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: String?): Single<List<Question>> =
        stackOverFlowRepository
            .getQuestionsByQuery(params ?: "")


}