package com.shenfeld.telegramcopy.utils

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.shenfeld.telegramcopy.R

fun Fragment.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.replaceActivity(activity: AppCompatActivity) {
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
    if (addToBackStack) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.main_data_container, fragment)
            .commit()
    } else {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_data_container, fragment)
            .commit()
    }
}

fun Fragment.replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
    if (addToBackStack) {
        this.fragmentManager?.beginTransaction()
            ?.addToBackStack(null)
            ?.replace(R.id.main_data_container, fragment)
            ?.commit()
    } else {
        this.fragmentManager?.beginTransaction()
            ?.replace(R.id.main_data_container, fragment)
            ?.commit()
    }
}