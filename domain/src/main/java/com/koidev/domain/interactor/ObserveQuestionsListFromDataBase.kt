package com.koidev.domain.interactor

import com.koidev.domain.Question
import com.koidev.domain.executor.PostExecutionThread
import com.koidev.domain.interactor.base.SingleUseCase
import com.koidev.domain.repository.StackOverFlowRepository
import io.reactivex.Single

class ObserveQuestionsListFromDataBase(
    postExecutionThread: PostExecutionThread,
    private val stackOverFlowRepository: StackOverFlowRepository
) : SingleUseCase<List<Question>, String?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: String?): Single<List<Question>> =
        stackOverFlowRepository
            .getQuestionsByQuery()
            .map { questions ->
//                if (params.isNullOrEmpty()) return questions
                questions.filter { item ->
                    val tags = item.tags.filter { it.contains(params as String) }
                    item.title.contains(params as String) || tags.isNotEmpty()
                }
            }


}