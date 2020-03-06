package com.koidev.stack_overflow_stars.di.common

import com.koidev.stack_overflow_stars.di.component.questions.QuestionsListSubComponent

interface ComponentManager {

    fun createQuestionsListSubComponent(): QuestionsListSubComponent

    fun releaseQuestionsListSubComponent()
}