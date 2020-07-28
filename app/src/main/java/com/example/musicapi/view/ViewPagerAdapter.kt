package com.example.musicapi.view

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fa: FragmentActivity, private val context: Context): FragmentStateAdapter(fa) {

    private val TAG = "ViewPagerAdapter"

    private lateinit var listener: IView
    companion object {
        var localPosition: Int = 0
    }

    override fun createFragment(position: Int): Fragment {
        if (context is MainActivity) listener = context // Late initialization happens here...
        Log.d(TAG, "createFragment: $position tab pressed...")
        listener.showProgress()
        return when(position) {
            0 -> {
                localPosition = 0
                FragmentRV()
            }
            1 -> {
                localPosition = 1
                FragmentRV()
            }
            else -> {
                localPosition = 2
                FragmentRV()
            }
        }
    }
    override fun getItemCount(): Int = 3
}