package com.gustu.github.data.repo

import com.gustu.github.data.model.DetailResponse
import com.gustu.github.data.model.FollowResponse
import com.gustu.github.data.model.SearchResponse
import com.gustu.github.retrofit.BASE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubImp(var base: BASE) : Github {
    override fun search(
        username: String,
        onSuccess: (SearchResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        base.api.search(username).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        onSuccess(response.body()!!)
                    } else {
                        onError("${response.code()} ${response.message()}")
                    }
                } else {
                    onError("${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                onError("${t}")
            }

        })
    }

    override fun detail(
        username: String,
        onSuccess: (DetailResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        base.api.detailUser(username).enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        onSuccess(response.body()!!)
                    } else {
                        onError("${response.code()} ${response.message()}")
                    }
                } else {
                    onError("${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                onError("${t}")
            }

        })
    }

    override fun followers(
        username: String,
        onSuccess: (List<FollowResponse>) -> Unit,
        onError: (String) -> Unit
    ) {
        base.api.followers(username).enqueue(object : Callback<List<FollowResponse>> {
            override fun onResponse(
                call: Call<List<FollowResponse>>,
                response: Response<List<FollowResponse>>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        onSuccess(response.body()!!)
                    } else {
                        onError("${response.code()} ${response.message()}")
                    }
                } else {
                    onError("${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FollowResponse>>, t: Throwable) {
                onError("${t}")
            }

        })
    }

    override fun following(
        username: String,
        onSuccess: (List<FollowResponse>) -> Unit,
        onError: (String) -> Unit
    ) {
        base.api.following(username).enqueue(object : Callback<List<FollowResponse>> {
            override fun onResponse(
                call: Call<List<FollowResponse>>,
                response: Response<List<FollowResponse>>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        onSuccess(response.body()!!)
                    } else {
                        onError("${response.code()} ${response.message()}")
                    }
                } else {
                    onError("${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FollowResponse>>, t: Throwable) {
                onError("${t}")
            }

        })
    }
}