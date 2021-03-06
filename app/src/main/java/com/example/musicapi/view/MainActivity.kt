package com.example.musicapi.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapi.R
import com.example.musicapi.model.Card
import com.example.musicapi.presenter.Presenter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IView {

    private val TAG = "MainActivity"
    private val presenter: Presenter = Presenter() // This is fine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FragmentRV.presenter = presenter
        showProgress()
        bindPresenter()
        swipeAdapter()
    }

    override fun bindPresenter() {
        presenter.onBind(this)
    }

    override fun swipeAdapter() {
        pager.adapter = ViewPagerAdapter(this, this)

        TabLayoutMediator(tab_layout, pager,
            TabLayoutMediator.TabConfigurationStrategy
            { tab, position
                -> when (position){
                0 -> tab.text = getString(R.string.tab_rock)
                1 -> tab.text = getString(R.string.tab_classic)
                2 -> tab.text = getString(R.string.tab_pop)
            }
            }).attach()
    }

    override fun onBackPressed() {
        if (pager.currentItem == 0) {
            super.onBackPressed()
        } else {
            pager.currentItem = pager.currentItem - 1
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun dismissProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun getMusic(genre: String) {
        presenter.getMusic(genre)
    }

    override fun displayData(dataSet: List<Card>, fragment: FragmentRV) {
        Log.d(TAG, "displayData: displayData is called with list...")
        fragment.displayData(dataSet, this)
    }
}