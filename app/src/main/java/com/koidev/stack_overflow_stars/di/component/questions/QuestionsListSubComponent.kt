package com.koidev.stack_overflow_stars.di.component.questions

import com.koidev.stack_overflow_stars.MainActivity
import com.koidev.stack_overflow_stars.di.module.questions.QuestionsListModule
import com.koidev.stack_overflow_stars.di.scope.QuestionsScope
import com.koidev.stack_overflow_stars.ui.questions.list.QuestionsListFragment
import dagger.Subcomponent

@QuestionsScope
@Subcomponent(modules = [QuestionsListModule::class])
interface QuestionsListSubComponent {
    fun inject(fragment: QuestionsListFragment)
    fun inject(activity: MainActivity)
}