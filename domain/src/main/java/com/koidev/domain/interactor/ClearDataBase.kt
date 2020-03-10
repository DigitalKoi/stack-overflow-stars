package com.koidev.domain.interactor

import com.koidev.domain.executor.PostExecutionThread
import com.koidev.domain.interactor.base.CompletableUseCase
import com.koidev.domain.repository.StackOverFlowRepository
import io.reactivex.Completable

class ClearDataBase (
    postExecutionThread: PostExecutionThread,
    private val stackOverFlowRepository: StackOverFlowRepository
): CompletableUseCase<Unit>(postExecutionThread) {

    override fun buildUseCaseCompletable(params: Unit?): Completable =
        stackOverFlowRepository.clearQuestions()
}