package com.shenfeld.telegramcopy.utils

import android.content.Context
import android.content.Intent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.shenfeld.telegramcopy.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_settings.*

fun showToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_SHORT).show()
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

fun hideKeyboard() {
    val imm: InputMethodManager =
        (APP_ACTIVITY.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).also {
            it.hideSoftInputFromWindow(APP_ACTIVITY.window.decorView.windowToken, 0)
        }
}

fun CircleImageView.downloadAndSetImage(url: String) {
    Picasso
        .get()
        .load(url)
        .placeholder(R.drawable.default_photo)
        .into(this)
}