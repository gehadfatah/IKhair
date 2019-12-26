package com.goda.ikhair.domain

import android.os.Parcel
import android.os.Parcelable
import com.goda.ikhair.model.data_model.Cause
import io.reactivex.Single

val causeListUseCasesDep by lazy {
    CauseListInteractor()
}

interface CauseListUseCases {
    fun getCausesList(): Single<List<Cause>>
}
