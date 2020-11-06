package com.shenfeld.telegramcopy.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shenfeld.telegramcopy.R
import com.shenfeld.telegramcopy.databinding.ActivityRegisterBinding
import com.shenfeld.telegramcopy.ui.fragments.EnterPhoneFragment
import com.shenfeld.telegramcopy.utils.initFirebase
import com.shenfeld.telegramcopy.utils.replaceFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initFirebase()
    }

    override fun onStart() {
        super.onStart()
        mToolbar = mBinding.toolbarRegister
        setSupportActionBar(mToolbar)
        title = getString(R.string.register_title_phone)
        replaceFragment(EnterPhoneFragment(), false)
    }
}