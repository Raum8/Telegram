package com.shenfeld.telegramcopy.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.shenfeld.telegramcopy.MainActivity
import com.shenfeld.telegramcopy.R
import kotlinx.android.synthetic.main.fragment_enter_phone.*

class EnterPhoneFragment : BaseFragment(R.layout.fragment_enter_phone) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enter_phone, container, false)
    }

    override fun onStart() {
        super.onStart()
        fab_register_next.setOnClickListener {
            sendCode()
        }
    }

    private fun sendCode() {
        if(et_register_phone_number.text.toString().isEmpty()) {
            Toast.makeText(activity, getString(R.string.register_toast_empty_phone), Toast.LENGTH_SHORT).show()
        } else {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.register_data_container, EnterCodeFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }

}