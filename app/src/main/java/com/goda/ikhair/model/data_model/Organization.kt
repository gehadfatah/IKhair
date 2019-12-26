package com.goda.ikhair.model.data_model

import com.google.gson.annotations.SerializedName

data class Organization(
        @SerializedName("-id")
        val id: String,
        @SerializedName("paymentMethods")
        val paymentMethods: PaymentMethods
)