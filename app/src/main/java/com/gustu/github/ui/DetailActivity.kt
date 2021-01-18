package com.gustu.github.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.gustu.github.R
import com.gustu.github.data.model.DetailResponse
import com.gustu.github.data.repo.GithubImp
import com.gustu.github.presenter.DetailPresenter
import com.gustu.github.presenter.DetailView
import com.gustu.github.retrofit.BASE
import com.gustu.github.ui.fragment.FollowerFragment
import com.gustu.github.ui.fragment.FollowingFragment
import com.gustu.github.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.*

class DetailActivity : AppCompatActivity(), DetailView {
    lateinit var presenter: DetailPresenter
    lateinit var username: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (SharedPrefUtil.getBoolean("isIndonesia")){
            val locale = Locale("id")
            val config: Configuration = baseContext.resources.configuration
            config.locale = locale
            baseContext.resources.updateConfiguration(
                config,
                baseContext.resources.displayMetrics
            )
        }
        else{
            val locale = Locale("en")
            val config: Configuration = baseContext.resources.configuration
            config.locale = locale
            baseContext.resources.updateConfiguration(
                config,
                baseContext.resources.displayMetrics
            )
        }
        setContentView(R.layout.activity_detail)
        username = intent.getStringExtra("username") ?: ""
        setTitle("${username}")
        val listFragment:MutableList<Fragment> = mutableListOf()
        listFragment.add(FollowerFragment(username))
        listFragment.add(FollowingFragment(username))
        initPresenter()
        showFragment(listFragment.get(0))
        swipeDetail.setOnRefreshListener {
            initPresenter()
        }
        tabDetailProfile.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                showFragment(listFragment.get(tab?.position!!))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                showFragment(listFragment.get(tab?.position!!))
            }

        })
    }

    private fun showFragment(get: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frameDetail,get).commit()
    }

    private fun initPresenter() {
        presenter = DetailPresenter(GithubImp(BASE()), this)
        presenter.getDetail(username)
    }

    override fun onSuccess(it: DetailResponse) {
        swipeDetail.isRefreshing = false
        Glide.with(this).load(it.avatarUrl).into(imgDetail)
        tvNamaProfile.setText(it.name)
        tvUsernameProfile.setText(it.login)
    }

    override fun onError(it: String) {
        swipeDetail.isRefreshing = false
        Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
    }
}