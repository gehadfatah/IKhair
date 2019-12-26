package com.goda.ikhair.model

import com.goda.ikhair.model.data_model.IkhairResponse
import io.reactivex.Single

val coinMarketCapRepositoryDep by lazy {
    CauseRepositoryImp()
}

interface CauseRepository {
    fun getCausesList(): Single<IkhairResponse>
}