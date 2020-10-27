package com.shenfeld.telegramcopy.ui.fragments

import androidx.fragment.app.Fragment
import com.shenfeld.telegramcopy.R
import com.shenfeld.telegramcopy.utils.AppTextWatcher
import com.shenfeld.telegramcopy.utils.showToast
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        et_enter_code.addTextChangedListener(AppTextWatcher {
            val string = et_enter_code.text.toString()
            if (string.length == 6)
                verifyCode()
        })
    }

    private fun verifyCode() {
        showToast("OK")
    }
}