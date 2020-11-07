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
import com.shenfeld.telegramcopy.models.UserModel
import com.shenfeld.telegramcopy.utils.*
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
        AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val dateMap = mutableMapOf<String, Any>()
                val uid = AUTH.currentUser?.uid.toString()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_PHONE] = mPhoneNumber
                //dateMap[CHILD_USERNAME] = USER.username
                REF_DATABASE_ROOT.child(NODE_USERS).child(uid)
                    .addListenerForSingleValueEvent(AppValueEventListener {
                        val userTemp = it.getValue(USER::class.java) ?: UserModel()
                        dateMap[CHILD_USERNAME] = userTemp.username
                    })

                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                    .addOnCompleteListener { onComplete ->
                        if (onComplete.isSuccessful) {
                            showToast("Добро пожаловать")
                            (APP_ACTIVITY).replaceActivity(MainActivity())
                        } else
                            showToast(onComplete.exception?.message.toString())
                    }
            } else {
                showToast(task.exception?.message.toString())
            }
        }
    }
}