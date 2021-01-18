package com.gustu.github.data.repo

import com.gustu.github.data.model.DetailResponse
import com.gustu.github.data.model.FollowResponse
import com.gustu.github.data.model.SearchResponse

interface Github {
    fun search(username: String, onSuccess: (SearchResponse) -> Unit, onError: (String) -> Unit)
    fun detail(username: String, onSuccess: (DetailResponse) -> Unit, onError: (String) -> Unit)
    fun followers(
        username: String,
        onSuccess: (List<FollowResponse>) -> Unit,
        onError: (String) -> Unit
    )

    fun following(
        username: String,
        onSuccess: (List<FollowResponse>) -> Unit,
        onError: (String) -> Unit
    )
}