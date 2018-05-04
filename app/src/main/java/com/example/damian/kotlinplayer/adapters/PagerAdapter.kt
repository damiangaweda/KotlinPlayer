package com.example.damian.kotlinplayer.adapters


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.damian.kotlinplayer.tabs.PlayerTab
import com.example.damian.kotlinplayer.tabs.PlaylistsTab
import com.example.damian.kotlinplayer.tabs.QueueTab

class PagerAdapter(fm: FragmentManager, internal var mNumOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        return when (position) {
            0 -> {
                val tab1 = QueueTab()
                tab1
            }
            1 -> {
                val tab2 = PlayerTab()
                tab2
            }
            2 -> {
                val tab3 = PlaylistsTab()
                tab3
            }
            else -> null
        }
    }

    override fun getCount(): Int {
        return mNumOfTabs
    }
}