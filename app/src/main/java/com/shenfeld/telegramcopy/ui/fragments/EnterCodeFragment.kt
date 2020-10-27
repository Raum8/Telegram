package com.shenfeld.telegramcopy.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.shenfeld.telegramcopy.R
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment : BaseFragment(R.layout.fragment_enter_code) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enter_code, container, false)
    }

    override fun onStart() {
        super.onStart()
        et_enter_code.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                val string = et_enter_code.text.toString()
                if(string.length == 6)
                    verifyCode()
            }

        })
    }

    private fun verifyCode() {
        Toast.makeText(activity, "Okay", Toast.LENGTH_SHORT).show()
    }
}