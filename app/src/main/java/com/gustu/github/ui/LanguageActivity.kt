package com.gustu.github.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.gustu.github.R
import com.gustu.github.utils.SharedPrefUtil
import kotlinx.android.synthetic.main.activity_language.*
import java.util.*

class LanguageActivity : AppCompatActivity() {
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
        setTitle(resources.getString(R.string.choose_lang))
        setContentView(R.layout.activity_language)
        btPilihIndonesia.setOnClickListener {
            SharedPrefUtil.saveBoolean("isIndonesia", true)
            val intent = Intent(this, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
        btPilihEnglish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            SharedPrefUtil.saveBoolean("isIndonesia", false)

        }
    }
}