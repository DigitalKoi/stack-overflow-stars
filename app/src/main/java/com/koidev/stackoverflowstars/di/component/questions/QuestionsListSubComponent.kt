package com.koidev.stackoverflowstars.di.component.questions

import com.koidev.stackoverflowstars.MainActivity
import com.koidev.stackoverflowstars.di.module.questions.QuestionsListModule
import com.koidev.stackoverflowstars.di.scope.QuestionsScope
import com.koidev.stackoverflowstars.ui.questions.list.QuestionsListFragment
import dagger.Subcomponent

@QuestionsScope
@Subcomponent(modules = [QuestionsListModule::class])
interface QuestionsListSubComponent {
    fun inject(fragment: QuestionsListFragment)
    fun inject(activity: MainActivity)
}