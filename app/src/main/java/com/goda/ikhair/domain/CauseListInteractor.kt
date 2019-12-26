package com.goda.ikhair.domain

import com.goda.ikhair.domain.common.mapNetworkErrors
import com.goda.ikhair.model.CauseRepository
import com.goda.ikhair.model.coinMarketCapRepositoryDep
import com.goda.ikhair.model.data_model.Cause
import io.reactivex.Single



class CauseListInteractor(private val causeRepository: CauseRepository = coinMarketCapRepositoryDep) : CauseListUseCases {


    override fun getCausesList(): Single<List<Cause>> {
        return causeRepository.getCausesList()
                .map { it.ikhair.country[0].causes.cause }
                .mapNetworkErrors()

    }

}