package com.example.musicapi.presenter

import android.util.Log
import com.example.musicapi.view.FragmentRV
import com.example.musicapi.view.IView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class Presenter {

    private val TAG = "Presenter"

    private lateinit var view: IView
    private lateinit var fragmentRV: FragmentRV

    fun onBind(view: IView) {
        this.view = view // Late initialization happens here...
    }

    fun findFragment(fragment: FragmentRV) {
        fragmentRV = fragment
    }

    fun getMusic(genre: String) {
        IMusicApi.getMusicApi().getMusic(genre) // Observable<MusicModel>
            .subscribeOn(Schedulers.io()) // Subscribe to worker thread
            .observeOn(AndroidSchedulers.mainThread()) // Observer
            .subscribe {
                Log.d(TAG, "getMusic: The list of results/Cards is: ${ it.results }")
                view.displayData(it.results, fragmentRV)
            }
    }
}