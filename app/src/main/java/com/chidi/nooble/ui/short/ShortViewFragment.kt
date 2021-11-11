package com.chidi.nooble.ui.short

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chidi.nooble.App
import com.chidi.nooble.databinding.FragmentShortViewBinding
import com.chidi.nooble.model.Short
import com.chidi.nooble.ui.model.MainViewModel
import com.chidi.nooble.utils.AppConstants
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory
import com.google.android.exoplayer2.util.Util

class ShortViewFragment : Fragment() {

    var isPlaying = false

    private var shortUrl: String? = null
    private var shortDataModel: Short? = null

    private var player: SimpleExoPlayer? = null
    private var cacheDataSourceFactory: CacheDataSourceFactory? = null
    private val simpleCache = App.simpleCache
    private var toPlayShortPosition: Int = -1


    companion object {
        fun newInstance(shortItem: Short) = ShortViewFragment()
            .apply {
                arguments = Bundle().apply {
                    putParcelable(AppConstants.KEY_SHORT_DATA, shortItem)
                }
            }
    }

    private var binding: FragmentShortViewBinding? = null

    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentShortViewBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, windowInsets ->
            v.updatePadding(top = windowInsets.systemWindowInsetTop)
            windowInsets.consumeSystemWindowInsets()
        }
        shortDataModel = arguments?.getParcelable(AppConstants.KEY_SHORT_DATA)
        // shortDataModel?.let { onItemClicked(it) }
        initData()

    }

    private fun initData() {
        binding?.apply {
            shortTitle.text = shortDataModel?.title
            creatorDetail.text = shortDataModel?.creator?.email
            shortDate.text = shortDataModel?.dateCreated
        }
        getPlayer()
        shortUrl = shortDataModel?.audioPath
        shortUrl?.let { prepareMedia(it) }

        binding?.shortParentLayout?.setOnClickListener {
            if (isPlaying) {
                playShort()
            } else {
                pauseShort()
            }
            isPlaying = !isPlaying
        }
    }

    fun onItemClicked(item: Short) {
        viewModel.selectItem(item)
    }

    private val playerCallback: Player.EventListener = object : Player.EventListener {
        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            Log.d(ShortViewFragment::class.java.simpleName, "onPlayerStateChanged playbackState: $playbackState")
            if (playbackState == Player.STATE_ENDED) {
                Log.d(ShortViewFragment::class.java.simpleName, "Player.STATE_ENDED")
                binding?.shortPlayingAnimView?.pauseAnimation()
                shortDataModel?.let {
                    onItemClicked(it)
                }
                return
            }
            if (playbackState == Player.STATE_READY) {
                binding?.shortPlayingAnimView?.resumeAnimation()
                return
            }
        }

        override fun onPlayerError(error: com.google.android.exoplayer2.ExoPlaybackException?) {
            super.onPlayerError(error)
            Log.e(ShortViewFragment::class.java.simpleName, "onPlayerError error: ${error?.localizedMessage}")
        }

        override fun onLoadingChanged(isLoading: Boolean) {
            super.onLoadingChanged(isLoading)
            if (isLoading) {
                binding?.apply {
                    shortPlayingAnimView.visibility = View.INVISIBLE
                    progressBar.visibility = View.VISIBLE
                }
            } else {
                binding?.apply {
                    shortPlayingAnimView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }
        }

    }

    private fun preparePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(context)
        cacheDataSourceFactory = CacheDataSourceFactory(
            simpleCache,
            DefaultHttpDataSourceFactory(
                Util.getUserAgent(
                    context,
                    "exo"
                )
            )
        )
    }

    private fun getPlayer(): SimpleExoPlayer? {
        if (player == null) {
            preparePlayer()
        }
        return player
    }

    private fun prepareMedia(linkUrl: String) {
        Log.d(ShortViewFragment::class.java.simpleName, "prepareMedia linkUrl: $linkUrl")

        val uri = Uri.parse(linkUrl)
        val mediaSource = ProgressiveMediaSource.Factory(cacheDataSourceFactory).createMediaSource(uri)

        player?.prepare(mediaSource, true, true)
        player?.repeatMode = Player.REPEAT_MODE_OFF
        player?.playWhenReady = true
        player?.addListener(playerCallback)

        toPlayShortPosition = -1
    }

    private fun playShort() {
        player?.playWhenReady = true
        binding?.shortPlayingAnimView?.playAnimation()
    }

    private fun replayShort() {
        if (player == null) {
            shortUrl?.let { prepareMedia(it) }
        } else {
            player?.seekToDefaultPosition()
            player?.playWhenReady = true
            binding?.shortPlayingAnimView?.resumeAnimation()
        }
    }

    private fun pauseShort() {
        player?.playWhenReady = false
        binding?.shortPlayingAnimView?.pauseAnimation()
    }

    private fun releasePlayer() {
        player?.stop(true)
        player?.release()
    }

    override fun onPause() {
        pauseShort()
        super.onPause()
    }

    override fun onResume() {
        replayShort()
        super.onResume()
    }

    override fun onDestroy() {
        releasePlayer()
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}