package com.koidev.remote.service.api

import com.koidev.remote.model.QuestionResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface StackOverFlowApi {

//    @GET("questions")
    @GET("orgs/kotlin/repos?")
    fun getQuestions(
        @Query("page") page: Int,
        @Query("pagesize") size: Int = 10,
        @Query("order") order: String = "desc",
        @Query("sort") sort: String = "activity",
        @Query("site") site: String = "stackoverflow"
    ): Single<QuestionResponse>

}