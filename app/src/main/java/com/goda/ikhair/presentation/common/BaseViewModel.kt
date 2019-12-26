package com.goda.ikhair.presentation.common

import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    internal val disposable: CompositeDisposable = CompositeDisposable()
    val isLoading = LiveEvent<Boolean>()
    val message = LiveEvent<String>()
    val error = LiveEvent<Throwable>()
    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}