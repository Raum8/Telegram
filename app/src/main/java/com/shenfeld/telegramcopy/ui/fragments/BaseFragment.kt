package com.shenfeld.telegramcopy.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shenfeld.telegramcopy.MainActivity
import com.shenfeld.telegramcopy.utils.APP_ACTIVITY

open class BaseFragment(layout: Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
        (APP_ACTIVITY).mAppDrawer.disableDrawer()
    }

    override fun onStop() {
        super.onStop()
        (APP_ACTIVITY).mAppDrawer.enableDrawer()
    }
}