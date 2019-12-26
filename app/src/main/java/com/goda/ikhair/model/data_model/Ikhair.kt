package com.goda.ikhair.model.data_model

import com.google.gson.annotations.SerializedName

data class Ikhair(
        @SerializedName("country")

        val country: List<Country>
)