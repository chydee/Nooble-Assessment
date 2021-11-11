package com.chidi.nooble

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.chidi.nooble.databinding.ActivityMainBinding
import com.chidi.nooble.model.Short
import com.chidi.nooble.ui.ShortItemsAdapter
import com.chidi.nooble.ui.model.MainViewModel
import com.chidi.nooble.utils.AppConstants
import com.chidi.nooble.utils.Result.Error
import com.chidi.nooble.utils.Result.Success
import com.chidi.nooble.work.PreCachingWorker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var pagerAdapter: ShortItemsAdapter

    private var binding: ActivityMainBinding? = null

    private var shorts: List<Short> = listOf()

    private var currentPlaying: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupContentWindow()
        changeStatusBarColor(Color.TRANSPARENT)
        setupObservers()


    }

    private fun setupObservers() {
        viewModel.shorts.observe(this) { result ->
            when (result) {
                is Success -> handleSuccess(result.data)
                is Error -> handleError(result.error)
            }
        }

        viewModel.selectedItem.observe(this, { item ->
            if (item != null) {
                currentPlaying = if (currentPlaying == shorts.size) {
                    0
                } else {
                    shorts.indexOf(item)
                }
                Log.d("MainActivity", "current page is: $currentPlaying")
                currentPlaying++
                Log.d("MainActivity", "next page is: $currentPlaying")
                binding?.mainViewPager?.setCurrentItem(currentPlaying, true)
            }
        })

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
        shorts = data
        pagerAdapter = ShortItemsAdapter(this, data as MutableList<Short>)
        binding?.mainViewPager?.adapter = pagerAdapter
        startPreCaching(data as ArrayList<Short>)
        Log.d("MainActivity", data.toString())
    }

    private fun setupContentWindow() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    private fun changeStatusBarColor(barColor: Int) {
        window.apply {
            statusBarColor = barColor
        }
    }

    private fun startPreCaching(dataList: ArrayList<Short>) {
        val urlList = arrayOfNulls<String>(dataList.size)
        dataList.mapIndexed { index, shortModel ->
            urlList[index] = shortModel.audioPath
        }
        val inputData = Data.Builder().putStringArray(AppConstants.KEY_SHORTS_LIST_DATA, urlList).build()
        val preCachingWork = OneTimeWorkRequestBuilder<PreCachingWorker>().setInputData(inputData)
            .build()
        WorkManager.getInstance(applicationContext)
            .enqueue(preCachingWork)
    }

/*override fun onShortEnded(shortItem: Short) {
    shorts?.indexOf(shortItem)?.let { binding?.mainViewPager?.setCurrentItem(it, true) }
}*/
}