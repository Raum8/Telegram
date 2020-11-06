package com.shenfeld.telegramcopy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.shenfeld.telegramcopy.activities.RegisterActivity
import com.shenfeld.telegramcopy.databinding.ActivityMainBinding
import com.shenfeld.telegramcopy.ui.fragments.ChatsFragment
import com.shenfeld.telegramcopy.ui.objects.AppDrawer
import com.shenfeld.telegramcopy.utils.AUTH
import com.shenfeld.telegramcopy.utils.initFirebase
import com.shenfeld.telegramcopy.utils.replaceActivity
import com.shenfeld.telegramcopy.utils.replaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunctionality()
    }

    private fun initFunctionality() {
        if (AUTH.currentUser != null) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment(), false)
        } else {
            replaceActivity(RegisterActivity())
        }
    }


    private fun initFields() {
        mToolbar = mBinding.toolbarMain
        mAppDrawer = AppDrawer(this, mToolbar)
        initFirebase()
    }
}