package com.goda.ikhair.model.data_model

import com.google.gson.annotations.SerializedName

data class IkhairResponse(
        @SerializedName("ikhair")

        val ikhair: Ikhair
)