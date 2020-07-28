package com.example.musicapi.view

import com.example.musicapi.model.Card

interface IView {

    fun bindPresenter()
    fun swipeAdapter()
    fun onBackPressed()
    fun showToast(message: String)
    fun showProgress()
    fun dismissProgress()
    fun getMusic(genre: String)
    fun displayData(dataSet: List<Card>, fragment: FragmentRV)
}