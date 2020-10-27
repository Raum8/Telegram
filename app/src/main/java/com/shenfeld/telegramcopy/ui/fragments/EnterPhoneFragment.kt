package com.shenfeld.telegramcopy.ui.fragments

import androidx.fragment.app.Fragment
import com.shenfeld.telegramcopy.R
import com.shenfeld.telegramcopy.utils.replaceFragment
import com.shenfeld.telegramcopy.utils.showToast
import kotlinx.android.synthetic.main.fragment_enter_phone.*

class EnterPhoneFragment : Fragment(R.layout.fragment_enter_phone) {

    override fun onStart() {
        super.onStart()
        fab_register_next.setOnClickListener {
            sendCode()
        }
    }

    private fun sendCode() {
        if (et_register_phone_number.text.toString().isEmpty()) {
          showToast(getString(R.string.register_toast_empty_phone))
        } else {
            replaceFragment(EnterCodeFragment(),true)
        }
    }

}