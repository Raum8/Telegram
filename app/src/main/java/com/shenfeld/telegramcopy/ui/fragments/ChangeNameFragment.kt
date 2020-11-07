package com.shenfeld.telegramcopy.ui.fragments

import com.shenfeld.telegramcopy.R
import com.shenfeld.telegramcopy.utils.*
import kotlinx.android.synthetic.main.fragment_change_name.*
import java.util.*

class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {

    override fun onResume() {
        super.onResume()
        initFullnameList()
    }

    private fun initFullnameList() {
        val fullNameList = USER.fullname.split(" ")
        if (fullNameList.size > 1) {
            settings_input_name.setText(fullNameList[0])
            settings_input_surname.setText(fullNameList[1])
        } else
            settings_input_name.setText(fullNameList[0])
    }

    override fun change() {
        val name = settings_input_name.text.toString()
        val surname = settings_input_surname.text.toString()

        if (name.isEmpty())
            showToast(getString(R.string.settings_toast_name))
        else {
            val fullName = "${name.capitalize(Locale.ROOT)} ${surname.capitalize(Locale.ROOT)}"
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_FULL_NAME).setValue(fullName)
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