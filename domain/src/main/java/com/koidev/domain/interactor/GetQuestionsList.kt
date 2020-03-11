package com.koidev.domain.interactor

import com.koidev.domain.executor.PostExecutionThread
import com.koidev.domain.interactor.base.CompletableUseCase
import com.koidev.domain.repository.StackOverFlowRepository
import io.reactivex.Completable

class GetQuestionsList(
    postExecutionThread: PostExecutionThread,
    private val stackOverFlowRepository: StackOverFlowRepository
) : CompletableUseCase<Int?>(postExecutionThread) {


    override fun buildUseCaseCompletable(params: Int?): Completable {
        if (params == null) throw IllegalArgumentException("Argument can't be null")

        return stackOverFlowRepository
            .getQuestionsByPage(params)
            .flatMapCompletable { stackOverFlowRepository.saveQuestions(ArrayList(it)) }
    }

}