package com.shenfeld.telegramcopy.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.shenfeld.telegramcopy.MainActivity
import com.shenfeld.telegramcopy.R

open class BaseChangeFragment(layout: Int) : Fragment(layout) {
    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        (activity as MainActivity).mAppDrawer.disableDrawer()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_confirm_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_changes -> change()
        }
        return true
    }

    open fun change() {

    }
}