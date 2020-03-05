package com.example.remote.implementations

import com.example.remote.service.api.StackOverFlowApi

class DefaultStackOverFlowRemote(
    private val api: StackOverFlowApi
) {

    fun getquestions(page: Int) = api
        .getQuestions(page)
        .map { /* add mapping here */ }
}