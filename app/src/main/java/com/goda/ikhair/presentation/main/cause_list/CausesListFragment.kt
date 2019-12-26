package com.goda.ikhair.presentation.main.cause_list

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Telephony
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goda.ikhair.R
import com.goda.ikhair.model.data_model.Cause
import com.goda.ikhair.presentation.common.AppAdapter
import com.goda.ikhair.presentation.common.showMessage
import com.goda.ikhair.presentation.common.toggleVisibility
import kotlinx.android.synthetic.main.causes_list_fragment.*
import kotlinx.android.synthetic.main.causes_list_fragment.view.*


fun newCausesListFragment() = CausesListFragment()
val Cause_LIST_FRAGMENT_TAG = CausesListFragment::class.java.name

class CausesListFragment : Fragment(), CauseCallback {
    private lateinit var viewModel: CauseListViewModel
    private val newListCauses: ArrayList<Cause> = ArrayList()

    private val mAdapterComics: AppAdapter<Cause> = AppAdapter(this, list = newListCauses)

    private fun observeViewModel() {
        viewModel.getCausesList()

        viewModel.causeList.observe(this, causesObserver)
        activity?.run {
            viewModel.error.observe(this, errorObserver)
            viewModel.isLoading.observe(this, loadingObserver)
        }
    }

    private val errorObserver: Observer<Throwable> = Observer {
        showMessage(it)
    }
    private val loadingObserver: Observer<Boolean> = Observer {
        progressCauses?.toggleVisibility(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CauseListViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.causes_list_fragment, container, false)

        observeViewModel()

        return view

    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView(view)

    }


    private fun initializeRecyclerView(view: View) {
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        view.apply {
            recyclerView.apply {
                mAdapterComics.setItemViewType(R.layout.cause_item)
                layoutManager = linearLayoutManager
                adapter = mAdapterComics
                addItemDecoration(
                        DividerItemDecoration(
                                activity,
                                LinearLayoutManager.VERTICAL
                        )
                )
            }

        }

    }

    private val causesObserver: Observer<List<Cause>> = Observer {

        showResultcauses(it)


    }


    private fun showResultcauses(newsResultsList: List<Cause>) {
        newListCauses.clear()
        newListCauses.addAll(newsResultsList)
        mAdapterComics.notifyItemRangeChanged(0, newsResultsList.size - 1)
    }

    override fun clickFaceShare(string: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, string)
        val pm: PackageManager = context!!.packageManager
        val activityList = pm.queryIntentActivities(shareIntent, 0)
        var installed: Boolean = false
        for (app in activityList) {
            if (app.activityInfo.name.contains("facebook")) {
                installed = true
                val activity = app.activityInfo
                val name = ComponentName(activity.applicationInfo.packageName, activity.name)
                shareIntent.addCategory(Intent.CATEGORY_LAUNCHER)
                shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                shareIntent.component = name
                startActivity(shareIntent)
                break
            }
        }
        if (installed) return
        try {

            val intent1 = Intent()
            intent1.setClassName("com.facebook.katana", "com.facebook.katana.activity.composer.ImplicitShareIntentHandler")
            intent1.action = "android.intent.action.SEND"
            intent1.type = "text/plain"
            intent1.putExtra("android.intent.extra.TEXT", string)
            startActivity(intent1)
        } catch (e: Exception) { // If we failed (not native FB app installed), try share through SEND
            val sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=https://www.ikhair.net/en/charitable-organizations"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl))
            startActivity(intent)
        }
    }

    override  fun clickopenSMS(string: String) {
        val message = string
        val phone = "5115"

        val uri = Uri.parse("smsto:+$phone")
        val intent = Intent(Intent.ACTION_SENDTO, uri)

        with(intent) {
            putExtra("address", "+$phone")
            putExtra("sms_body", message)
        }

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
                //Getting the default sms app.
                val defaultSmsPackageName = Telephony.Sms.getDefaultSmsPackage(context)

                // Can be null in case that there is no default, then the user would be able to choose
                // any app that support this intent.
                if (defaultSmsPackageName != null) intent.setPackage(defaultSmsPackageName)

                startActivity(intent)
            }
            else -> startActivity(intent)
        }
    }

    override fun clickTwitShare(string: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/intent/tweet?text=$string"))
        startActivity(browserIntent)
    }

    override fun clickDefaultShare(string: String) {
        val shareBody = string
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.shareSubject))
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
        startActivity(Intent.createChooser(sharingIntent, "Share Using"))
    }


}