package com.shenfeld.telegramcopy.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.PhoneAuthProvider
import com.shenfeld.telegramcopy.MainActivity
import com.shenfeld.telegramcopy.R
import com.shenfeld.telegramcopy.activities.RegisterActivity
import com.shenfeld.telegramcopy.utils.AUTH
import com.shenfeld.telegramcopy.utils.AppTextWatcher
import com.shenfeld.telegramcopy.utils.replaceActivity
import com.shenfeld.telegramcopy.utils.showToast
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment(private val mPhoneNumber: String, private val id: String) :
    Fragment(R.layout.fragment_enter_code) {

    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = mPhoneNumber
        et_enter_code.addTextChangedListener(AppTextWatcher {
            val string = et_enter_code.text.toString()
            if (string.length == 6)
                enterCode()
        })
    }

    private fun enterCode() {
        val code = et_enter_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                showToast("Добро пожаловать")
                (activity as RegisterActivity).replaceActivity(MainActivity())
            } else {
                showToast(it.exception?.message.toString())
            }
        }
    }
}