package com.shenfeld.telegramcopy.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.shenfeld.telegramcopy.MainActivity
import com.shenfeld.telegramcopy.R
import com.shenfeld.telegramcopy.activities.RegisterActivity
import com.shenfeld.telegramcopy.utils.AUTH
import com.shenfeld.telegramcopy.utils.replaceActivity
import com.shenfeld.telegramcopy.utils.replaceFragment

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {
    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
            R.id.settings_menu_change_name -> {
                replaceFragment(ChangeNameFragment(), true)
            }
        }
        return true
    }

}