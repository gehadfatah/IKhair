package com.goda.ikhair.presentation.common

sealed class ViewState<T> {
    abstract val data: T
}

data class DefaultState<T>(override val data: T) : ViewState<T>()
data class LoadingState<T>(override val data: T) : ViewState<T>()
data class ErrorState<T>(override val data: T, val error: Throwable) : ViewState<T>()
