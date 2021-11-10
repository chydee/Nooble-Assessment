package com.chidi.nooble.utils

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.chidi.nooble.R
import com.google.android.material.snackbar.Snackbar

fun circularProgress(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 4.0F
        centerRadius = 20.0F
        setColorSchemeColors(Color.rgb(31, 9, 81), Color.rgb(3, 218, 197))
        start()
    }
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

fun View.showMessage(message: Message) {
    val snackbar = Snackbar.make(this, message.message, Snackbar.LENGTH_LONG)
    if (message.caution)
        snackbar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.purple_200))
    else
        snackbar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.teal_200))
    snackbar.show()
}