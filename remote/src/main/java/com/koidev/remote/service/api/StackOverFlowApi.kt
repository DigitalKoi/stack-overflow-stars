package com.koidev.remote.service.api

import com.koidev.remote.model.QuestionResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface StackOverFlowApi {

    @GET("questions")
    fun getQuestions(
        @Query("page") page: Int
    ): Observable<QuestionResponse>

}