package com.gustu.github.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.gustu.github.LanguageActivity
import com.gustu.github.R
import com.gustu.github.data.model.SearchResponse
import com.gustu.github.data.repo.GithubImp
import com.gustu.github.presenter.MainPresenter
import com.gustu.github.presenter.MainView
import com.gustu.github.retrofit.BASE
import com.gustu.github.ui.adapter.UsersAdapter
import com.gustu.github.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), MainView {
    lateinit var presenter: MainPresenter
    lateinit var adapter: UsersAdapter
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
        Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.language, menu)
        if (SharedPrefUtil.getBoolean("isIndonesia")) {
            menu?.getItem(0)?.setIcon(ContextCompat.getDrawable(this, R.drawable.indonesia))
        } else {
            menu?.getItem(0)?.setIcon(ContextCompat.getDrawable(this, R.drawable.english))
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.language -> {
                startActivity(Intent(this, LanguageActivity::class.java))
                return true
            }

        }
        return true
    }
}