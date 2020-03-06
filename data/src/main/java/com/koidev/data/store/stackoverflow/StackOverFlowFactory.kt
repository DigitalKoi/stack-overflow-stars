package com.koidev.data.store.stackoverflow

class StackOverFlowFactory(
    private val stackOverFlowCacheDataStore: StackOverFlowCacheDataStore,
    private val stackOverFlowRemoteDataStore: StackOverFlowRemoteDataStore
) {

    fun getStackOverFlowRemoteDataStore() = stackOverFlowRemoteDataStore

    fun getStackOverFlowCacheDataStore() = stackOverFlowCacheDataStore
}