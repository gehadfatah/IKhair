package com.goda.ikhair.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.goda.ikhair.R
import com.goda.ikhair.presentation.common.replaceFragment
import com.goda.ikhair.presentation.main.cause_list.Cause_LIST_FRAGMENT_TAG
import com.goda.ikhair.presentation.main.cause_list.newCausesListFragment

class MainActivity : AppCompatActivity() {
    private val causesListFragment by lazy { newCausesListFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) replaceFragment(R.id.container, causesListFragment, Cause_LIST_FRAGMENT_TAG)
    }
}
