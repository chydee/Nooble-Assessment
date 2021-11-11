package com.chidi.nooble

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.chidi.nooble.model.Short
import com.chidi.nooble.ui.model.MainViewModel
import com.chidi.nooble.utils.Result.Error
import com.chidi.nooble.utils.Result.Success
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupContentWindow()
        changeStatusBarColor(R.color.primaryDarkColor)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.shorts.observe(this) { result ->
            when (result) {
                is Success -> handleSuccess(result.data)
                is Error -> handleError(result.error)
            }
        }
    }

    private fun handleError(error: Throwable) {
        Toast.makeText(
            applicationContext,
            error.localizedMessage,
            Toast.LENGTH_LONG
        )
            .show()
    }

    private fun handleSuccess(data: List<Short>) {
        Log.d("MainActivity", data.toString())
    }

    private fun setupContentWindow() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    private fun changeStatusBarColor(@ColorRes barColor: Int) {
        window.apply {
            statusBarColor = getColorFromRes(barColor)
        }
    }

    private fun getColorFromRes(barColor: Int): Int {
        return ContextCompat.getColor(this, barColor)
    }
}