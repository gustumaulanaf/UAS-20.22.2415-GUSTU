package com.gustu.github.presenter

import com.gustu.github.data.model.FollowResponse
import com.gustu.github.data.repo.GithubImp

class FollowingPresenter(val imp: GithubImp, val view: FollowingView) {
    fun getFollowing(username: String) {
        imp.following(username, {
            view.onSuccess(it)
        }, {
            view.onError(it)
        })
    }
}

interface FollowingView {
    fun onSuccess(it: List<FollowResponse>)
    fun onError(it: String)

}