package com.goda.ikhair.domain.common

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.UnknownHostException

fun <T> Single<T>.retryIfOffline(): Single<T> {
    return retry { err ->
        val retry: Boolean = (err is UnknownHostException)
        if (retry) Thread.sleep(3000)
        retry
    }
}

fun <T> Single<T>.mapNetworkErrors(): Single<T> {
    return onErrorResumeNext { error ->
        when (error) {
            is HttpException -> {
                when {
                    error.code() == 401 -> Single.error(UnAuthorizedException())
                    else -> Single.error(InternalServerErrorException())
                }
            }
            else -> Single.error(error)
        }
    }
}

fun Completable.mapNetworkErrors(): Completable {
    return onErrorResumeNext { error ->
        when (error) {
            is HttpException -> {
                when {
                    error.code() == 401 -> Completable.error(UnAuthorizedException())
                    else -> Completable.error(InternalServerErrorException())
                }
            }
            else -> Completable.error(error)
        }
    }
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun Completable.applyIoScheduler() = applyScheduler(Schedulers.io())

fun Completable.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun Completable.applyScheduler(scheduler: Scheduler) =
        subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

fun <T> Single<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun <T> Single<T>.applyScheduler(scheduler: Scheduler) =
        subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

