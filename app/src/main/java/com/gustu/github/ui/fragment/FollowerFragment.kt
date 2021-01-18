package com.gustu.github.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gustu.github.R
import com.gustu.github.data.model.FollowResponse
import com.gustu.github.data.repo.GithubImp
import com.gustu.github.presenter.FollowerPresenter
import com.gustu.github.presenter.FollowerView
import com.gustu.github.retrofit.BASE
import com.gustu.github.ui.adapter.FollowerAdapter
import kotlinx.android.synthetic.main.fragment_follower.view.*


class FollowerFragment(var username:String): Fragment(), FollowerView {
    private lateinit var mContext: Context
    lateinit var root: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_follower, container, false)
        initPresenter()
        return root
    }

    private fun initPresenter() {
        val presenter = FollowerPresenter(GithubImp(BASE()),this)
        presenter.getFollower(username)
    }

    override fun onSuccess(it: List<FollowResponse>) {
        val adapter = FollowerAdapter(it, mContext)
        root.rvFollowers.layoutManager = LinearLayoutManager(mContext)
        root.rvFollowers.hasFixedSize()
        adapter.notifyDataSetChanged()
        root.rvFollowers.adapter = adapter
    }

    override fun onError(it: String) {
        Toast.makeText(mContext, it, Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}