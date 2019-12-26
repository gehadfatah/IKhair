package com.goda.ikhair.domain.common


fun Float.formatTo(numberOfDecimals: Int) =
        String.format("%.${numberOfDecimals}f", this)