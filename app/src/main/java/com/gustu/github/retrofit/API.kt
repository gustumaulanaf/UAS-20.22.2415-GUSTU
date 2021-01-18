package com.gustu.github.retrofit

import com.gustu.github.data.model.DetailResponse
import com.gustu.github.data.model.FollowResponse
import com.gustu.github.data.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET("users/{id}")
    fun detailUser(
        @Path("id") id: String
    ): Call<DetailResponse>

    @GET("search/users")
    fun search(
        @Query("q") username: String
    ): Call<SearchResponse>

    @GET("users/{username}/followers")
    fun followers(
        @Path("username") username: String
    ): Call<List<FollowResponse>>

    @GET("users/{username}/following")
    fun following(
        @Path("username") username: String
    ): Call<List<FollowResponse>>
}