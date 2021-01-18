package com.gustu.github.presenter

import com.gustu.github.data.model.DetailResponse
import com.gustu.github.data.repo.GithubImp

class DetailPresenter(val imp: GithubImp, val view: DetailView) {
    fun getDetail(username: String) {
        imp.detail(username, {
            view.onSuccess(it)
        }, {
            view.onError(it)
        })
    }
}

interface DetailView {
    fun onSuccess(it: DetailResponse)
    fun onError(it: String)

}