package com.goda.ikhair.model

import com.goda.ikhair.model.data_model.IkhairResponse
import io.reactivex.Single

class CauseRepositoryImp(private val causeApi: CauseApi = CAUSE_API_DEP) : CauseRepository {
    override fun getCausesList(): Single<IkhairResponse> = causeApi.getCausesList()
}