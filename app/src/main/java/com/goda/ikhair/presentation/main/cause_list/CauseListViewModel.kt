package com.goda.ikhair.presentation.main.cause_list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.goda.ikhair.domain.common.addTo
import com.goda.ikhair.presentation.common.androidMainThreadScheduler
import com.goda.ikhair.presentation.common.schedulerIo
import com.goda.ikhair.domain.CauseListUseCases
import com.goda.ikhair.domain.causeListUseCasesDep
import com.goda.ikhair.model.data_model.Cause
import com.goda.ikhair.presentation.common.*
import io.reactivex.Scheduler


private val TAG = CauseListViewModel::class.java.name

class CauseListViewModel(private val causeListUseCases: CauseListUseCases = causeListUseCasesDep, private val subscribeOnScheduler: Scheduler = schedulerIo, private val observeOnScheduler: Scheduler = androidMainThreadScheduler) : BaseViewModel() {

    val causeList: MutableLiveData<List<Cause>> = MutableLiveData()


    fun getCausesList() {
        causeListUseCases.getCausesList()
                .doOnSubscribe {
                    isLoading.postValue(true)
                }.subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe({

                    it?.let {
                        causeList.value = it
                    }
                    isLoading.postValue(false)

                }, {
                    isLoading.postValue(false)
                    error.postValue(it)
                    Log.v(" ",it.toString())
                }).addTo(disposable)
    }


}