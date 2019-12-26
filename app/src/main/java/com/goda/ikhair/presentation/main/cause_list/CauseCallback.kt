package com.goda.ikhair.presentation.main.cause_list

import com.goda.marvel.presentation.common.RecyclerViewCallback

interface CauseCallback:RecyclerViewCallback {
    fun clickFaceShare(string: String)
    fun clickTwitShare(string: String)
    fun clickopenSMS(string: String)
    fun clickDefaultShare(string: String)
}