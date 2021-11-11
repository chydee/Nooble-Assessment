package com.chidi.nooble.utils

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chidi.nooble.R
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.material.snackbar.Snackbar
import java.util.concurrent.TimeUnit

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
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

fun Long.toTimeMinsSecs(): String {
    val milliseconds: Long = this

    val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)

    return "$minutes:$seconds"
}

var SimpleExoPlayer.playbackSpeed: Float
    get() = playbackParameters?.speed ?: 1f
    set(speed) {
        val pitch = playbackParameters?.pitch ?: 1f
        playbackParameters = PlaybackParameters(speed, pitch)
    }