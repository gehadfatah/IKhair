package com.goda.ikhair.model.data_model

import com.google.gson.annotations.SerializedName

data class PaymentMethods(
        @SerializedName("Bank")

        val Bank: List<Any>,
        @SerializedName("SMS")

        val SMS: List<SMS>
)