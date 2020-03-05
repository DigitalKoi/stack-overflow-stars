package com.koidev.data.questions

class DefaultStackOverFlowRepository(
    private val stackOverFlowRemote: StackOverFlowRemote
) {

    fun getQuestions(page: Int) = stackOverFlowRemote
        .getQuestions(page)
}