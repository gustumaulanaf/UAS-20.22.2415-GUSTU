package com.gustu.github.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.gustu.github.R
import com.gustu.github.data.model.SearchResponse
import com.gustu.github.data.repo.GithubImp
import com.gustu.github.presenter.MainPresenter
import com.gustu.github.presenter.MainView
import com.gustu.github.retrofit.BASE
import com.gustu.github.ui.adapter.UsersAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {
    lateinit var presenter: MainPresenter
    lateinit var adapter: UsersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initPresenter()
    }

    private fun initView() {
        searchMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.search(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun initPresenter() {
        presenter = MainPresenter(GithubImp(BASE()), this)
    }

    override fun onSuccess(it: SearchResponse) {
        adapter = UsersAdapter(it, this)
        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.hasFixedSize()
        rvUsers.adapter = adapter
    }

    override fun onError(it: String) {
        TODO("Not yet implemented")
    }
}