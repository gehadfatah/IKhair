package com.goda.ikhair.model.data_model

import com.google.gson.annotations.SerializedName

data class SMS(
        @SerializedName("-keyword")

        val keyword: String,
        @SerializedName("-operator")

        val operator: String,
        @SerializedName("-operatorEn")

        val operatorEn: String,
        @SerializedName("-price")

        val price: Int,
        @SerializedName("-shortcode")

        val shortcode: Int
)