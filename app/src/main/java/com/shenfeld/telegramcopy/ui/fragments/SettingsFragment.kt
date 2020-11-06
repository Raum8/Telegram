package com.shenfeld.telegramcopy.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.shenfeld.telegramcopy.MainActivity
import com.shenfeld.telegramcopy.R
import com.shenfeld.telegramcopy.activities.RegisterActivity
import com.shenfeld.telegramcopy.utils.AUTH
import com.shenfeld.telegramcopy.utils.USER
import com.shenfeld.telegramcopy.utils.replaceActivity
import com.shenfeld.telegramcopy.utils.replaceFragment
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {
    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()
    }

    private fun initFields() {
        tv_settings_label_bio.text = USER.bio
        tv_settings_full_name.text = USER.fullname
        tv_settings_phone_number.text = USER.phone
        settings_status.text = USER.status
        tv_settings_change_username.text = USER.username

        cl_settings_change_username.setOnClickListener {
            replaceFragment(ChangeUserNameFragment(), true)
        }
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