package com.goda.ikhair.presentation.common

sealed class ViewStateWithPagination<T> {
    abstract val pageNum: Int
    abstract val loadedAllItems: Boolean
    abstract val data: T
}

data class DefaultPageState<T>(override val pageNum: Int, override val loadedAllItems: Boolean, override val data: T) : ViewStateWithPagination<T>()
data class LoadingPageState<T>(override val pageNum: Int, override val loadedAllItems: Boolean, override val data: T) : ViewStateWithPagination<T>()
data class PaginatingState<T>(override val pageNum: Int, override val loadedAllItems: Boolean, override val data: T) : ViewStateWithPagination<T>()
data class ErrorPageState<T>(val error: Throwable, override val pageNum: Int, override val loadedAllItems: Boolean, override val data: T) : ViewStateWithPagination<T>()