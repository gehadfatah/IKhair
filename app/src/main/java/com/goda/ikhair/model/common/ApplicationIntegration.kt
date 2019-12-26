package com.goda.ikhair.model.common

import android.app.Application
import java.lang.ref.WeakReference


object ApplicationIntegration {

    private lateinit var applicationReference : WeakReference<Application>

    fun with(application: Application){
        applicationReference = WeakReference(application)
    }

    fun getApplication() = applicationReference.get()!!
}