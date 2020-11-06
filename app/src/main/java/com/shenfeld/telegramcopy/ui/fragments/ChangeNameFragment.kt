package com.shenfeld.telegramcopy.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.shenfeld.telegramcopy.MainActivity
import com.shenfeld.telegramcopy.R
import com.shenfeld.telegramcopy.utils.*
import kotlinx.android.synthetic.main.fragment_change_name.*
import java.util.*

class ChangeNameFragment : BaseFragment(R.layout.fragment_change_name) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        val fullNameList = USER.fullname.split(" ")
        if(fullNameList.size > 1) {
            settings_input_name.setText(fullNameList[0])
            settings_input_surname.setText(fullNameList[1])
        } else
            settings_input_name.setText(fullNameList[0])
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_confirm_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_changes -> changeName()
        }
        return true
    }

    private fun changeName() {
        val name = settings_input_name.text.toString()
        val surname = settings_input_surname.text.toString()

        if (name.isEmpty())
            showToast(getString(R.string.settings_toast_name))
        else {
            val fullName = "${name.capitalize(Locale.ROOT)} ${surname.capitalize(Locale.ROOT)}"
            REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_FULL_NAME).setValue(fullName)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast(getString(R.string.toast_data_update))
                        USER.fullname = fullName
                        fragmentManager?.popBackStack()
                    } else {
                        showToast("")
                    }
                }
        }

    }
}