package com.shenfeld.telegramcopy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shenfeld.telegramcopy.activities.RegisterActivity
import com.shenfeld.telegramcopy.databinding.ActivityMainBinding
import com.shenfeld.telegramcopy.ui.fragments.ChatsFragment
import com.shenfeld.telegramcopy.ui.objects.AppDrawer

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer
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
        if(false) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_data_container, ChatsFragment()).commit()
        } else {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }


    private fun initFields() {
        mToolbar = mBinding.toolbarMain
        mAppDrawer = AppDrawer(this, mToolbar)
    }
}