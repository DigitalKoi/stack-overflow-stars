package com.koidev.stackoverflowstars.global

import com.koidev.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class BackgroundThread : PostExecutionThread {
    override val scheduler: Scheduler
        get() = Schedulers.io()
}