package com.gustu.github.presenter

import com.gustu.github.data.model.FollowResponse
import com.gustu.github.data.repo.GithubImp

class FollowerPresenter(val imp: GithubImp, val view: FollowerView) {
    fun getFollower(username: String) {
        imp.followers(username, {
            view.onSuccess(it)
        }, {
            view.onError(it)
        })
    }
}

interface FollowerView {
    fun onSuccess(it: List<FollowResponse>)
    fun onError(it: String)

}