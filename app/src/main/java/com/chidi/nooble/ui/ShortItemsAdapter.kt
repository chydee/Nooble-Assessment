package com.chidi.nooble.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chidi.nooble.model.Short
import com.chidi.nooble.ui.short.ShortViewFragment

class ShortItemsAdapter(fragment: Fragment, private val shortItems: MutableList<Short> = mutableListOf()) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return shortItems.size
    }

    override fun createFragment(position: Int): Fragment {
        return ShortViewFragment.newInstance(shortItems[position])
    }
}