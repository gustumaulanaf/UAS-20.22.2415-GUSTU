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
import com.gustu.github.presenter.FollowingPresenter
import com.gustu.github.presenter.FollowingView
import com.gustu.github.retrofit.BASE
import com.gustu.github.ui.adapter.FollowingAdapter
import kotlinx.android.synthetic.main.fragment_following.view.*


class FollowingFragment(val username: String) : Fragment(), FollowingView {

    private lateinit var mContext: Context
    lateinit var presenter:FollowingPresenter
    lateinit var root: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_following, container, false)
        initPresenter()
        return root
    }

    private fun initPresenter() {
        presenter = FollowingPresenter(GithubImp(BASE()),this)
        presenter.getFollowing(username)
    }

    override fun onSuccess(it: List<FollowResponse>) {
        val adapter = FollowingAdapter(it, mContext)
        root.rvFollowing.layoutManager = LinearLayoutManager(mContext)
        root.rvFollowing.hasFixedSize()
        root.rvFollowing.adapter = adapter
    }

    override fun onError(it: String) {
        Toast.makeText(mContext, it, Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}