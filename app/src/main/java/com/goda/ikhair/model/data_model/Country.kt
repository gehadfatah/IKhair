package com.goda.ikhair.model.data_model

import com.google.gson.annotations.SerializedName

data class Country(
        @SerializedName("-causes")

        val causes: Causes,
        @SerializedName("-id")

        val id: String
)