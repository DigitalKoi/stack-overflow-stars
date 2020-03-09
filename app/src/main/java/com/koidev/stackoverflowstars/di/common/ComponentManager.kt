package com.koidev.stackoverflowstars.di.common

import com.koidev.stackoverflowstars.di.component.questions.QuestionsListSubComponent

interface ComponentManager {

    fun createQuestionsListSubComponent(): QuestionsListSubComponent

    fun releaseQuestionsListSubComponent()
}