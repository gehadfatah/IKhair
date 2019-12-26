package com.goda.ikhair.model.data_model

import com.google.gson.annotations.SerializedName

data class Causes(
        @SerializedName("cause")
        val cause: List<Cause>
)