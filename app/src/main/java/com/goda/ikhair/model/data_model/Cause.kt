package com.goda.ikhair.model.data_model

import com.google.gson.annotations.SerializedName

data class Cause(
        @SerializedName("-collectedAmount")

        val collectedAmount: Int,
        @SerializedName("-contact")

        val contact: String,
        @SerializedName("-id")

        val id: String,
        @SerializedName("-info")

        val info: String,
        @SerializedName("-name")

        val name: String,
        @SerializedName("-nameEn")

        val nameEn: String,
        @SerializedName("-targetAmount")

        val targetAmount: Int,
        @SerializedName("organization")

        val organization: Organization
)