package com.shenfeld.telegramcopy.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.shenfeld.telegramcopy.MainActivity
import com.shenfeld.telegramcopy.R
import com.shenfeld.telegramcopy.utils.*
import kotlinx.android.synthetic.main.fragment_change_user_name.*
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.*

class ChangeUserNameFragment : BaseFragment(R.layout.fragment_change_user_name) {

    lateinit var mNewUsername: String

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        settings_input_username.setText(USER.username)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_confirm_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_changes -> changeUsername()
        }
        return true
    }

    fun changeUsername() {
        mNewUsername = settings_input_username.text.toString().toLowerCase(Locale.getDefault())
        if (mNewUsername.isEmpty())
            showToast("Имя пользователя пустое")
        else {
            REF_DATABASE_ROOT.child(NODE_USERNAMES)
                .addListenerForSingleValueEvent(AppValueEventListener {
                    if (it.hasChild(mNewUsername)) {
                        showToast("Имя уже занято")
                    } else
                        change()
                })
        }
    }

    private fun change() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(mNewUsername).setValue(UID)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    updateCurrentUsername()
                } else {
                    showToast("BAD")
                }
            }
    }

    private fun updateCurrentUsername() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_USERNAME).setValue(mNewUsername)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    deleteOldUsername()
                } else
                    showToast(it.exception?.message.toString())

            }
    }

    private fun deleteOldUsername() {
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(USER.username).removeValue()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast(getString(R.string.toast_data_update))
                    fragmentManager?.popBackStack()
                    USER.username = mNewUsername
                } else
                    showToast(it.exception?.message.toString())
            }
    }
}