package com.chidi.nooble.ui.short

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import com.chidi.nooble.databinding.FragmentShortViewBinding
import com.chidi.nooble.model.Short
import com.chidi.nooble.utils.AppConstants

class ShortViewFragment : Fragment() {

    companion object {
        fun newInstance(shortItem: Short) = ShortViewFragment()
            .apply {
                arguments = Bundle().apply {
                    putParcelable(AppConstants.KEY_SHORT_DATA, shortItem)
                }
            }
    }

    private var binding: FragmentShortViewBinding? = null

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}