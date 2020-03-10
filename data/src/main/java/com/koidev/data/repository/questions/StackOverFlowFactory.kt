package com.koidev.data.repository.questions

class StackOverFlowFactory(
    private val stackOverFlowCache: StackOverFlowCache,
    private val stackOverFlowRemote: StackOverFlowRemote
) {

    fun getStackOverFlowRemote() = stackOverFlowRemote

    fun getStackOverFlowCache() = stackOverFlowCache
}