package com.koidev.remote.service.api

import com.koidev.remote.model.QuestionResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface StackOverFlowApi {

    @GET("questions")
    fun getQuestions(
        @Query("page") page: Int,
        @Query("pagesize") size: Int = 20,
        @Query("order") order: String = "desc",
        @Query("sort") sort: String = "activity",
        @Query("site") site: String = "stackoverflow"
    ): Observable<QuestionResponse>

}