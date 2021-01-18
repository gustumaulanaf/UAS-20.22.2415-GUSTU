package com.gustu.github.presenter

import com.gustu.github.data.model.SearchResponse
import com.gustu.github.data.repo.GithubImp

class MainPresenter(val imp: GithubImp, val view: MainView) {
    fun search(username: String) {
        imp.search(username, {
            view.onSuccess(it)
        }, {
            view.onError(it)
        })
    }
}

interface MainView {
    fun onSuccess(it: SearchResponse)
    fun onError(it: String)
}