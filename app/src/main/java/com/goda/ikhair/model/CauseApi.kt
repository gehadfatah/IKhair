package com.goda.ikhair.model

import com.goda.ikhair.model.common.retrofitClient
import com.goda.ikhair.model.data_model.IkhairResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

val CAUSE_API_DEP : CauseApi by lazy {
    retrofitClient.create(CauseApi::class.java)
}

interface CauseApi {
   @GET("causes")
    fun getCausesList( @Query("country") country: String = "AE"): Single<IkhairResponse>

}


