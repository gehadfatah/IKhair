package com.goda.ikhair.domain.common

import android.graphics.Typeface
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.goda.ikhair.CausesApplication
import com.goda.ikhair.R
import com.goda.ikhair.model.data_model.Cause
import github.nisrulz.stackedhorizontalprogressbar.StackedHorizontalProgressBar


object CustomBindingAdapter {
    @JvmStatic
    @BindingAdapter("typeFace")
    fun setTypeFace(view: View?, type: String) {
        if (view != null && !TextUtils.isEmpty(type)) {
            (view as TextView).typeface = Typeface.createFromAsset(view.context.assets, type)
        }
    }

    @BindingAdapter("progres")
    @JvmStatic
    fun setImage(view: TextView?, cause: Cause?) {
        cause?.let {
           // CausesApplication.applicationContext().getString(R.string.progress_format)
         //  view?.text= "${((cause.collectedAmount.toFloat()) / (cause.targetAmount.toFloat())).times(100)} %"
            view?.text= String.format("%.1f", ((cause.collectedAmount.toFloat()) / (cause.targetAmount.toFloat())).times(100))+" %"


        }

    }
}