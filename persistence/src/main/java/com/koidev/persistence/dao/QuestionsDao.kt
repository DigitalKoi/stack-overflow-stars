package com.koidev.persistence.dao

import androidx.room.Dao
import androidx.room.Query
import com.koidev.persistence.constant.QuestionConstants
import com.koidev.persistence.model.CachedQuestions
import io.reactivex.Observable

@Dao
abstract class QuestionsDao : BaseDao<CachedQuestions> {

    @Query(QuestionConstants.CLEAR_QUESTIONS)
    abstract fun clear()

    @Query(QuestionConstants.QUERY_QUESTIONS)
    abstract fun getQuestions(): Observable<ArrayList<CachedQuestions>>
}